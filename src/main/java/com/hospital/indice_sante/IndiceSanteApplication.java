package com.hospital.indice_sante;

import com.hospital.indice_sante.repository.PatientRepository;
import com.hospital.indice_sante.service.DiagnosisService;
import com.hospital.indice_sante.service.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@SpringBootApplication
public class IndiceSanteApplication {

    public IndiceSanteApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
		SpringApplication.run(IndiceSanteApplication.class, args);
	}
	private final PasswordEncoder passwordEncoder;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	@Bean
	CommandLineRunner run(PatientService patientService,
						  DiagnosisService diagnosisService,
						  PatientRepository patientRepository) {
		return args -> {

			Scanner scanner = new Scanner(System.in);

			System.out.println("=================================");
			System.out.println("Bienvenue sur la borne de diagnostic");
			System.out.println("=================================");

			while (true) {

				System.out.println("\nChoisissez une option :");
				System.out.println("1 - S'inscrire");
				System.out.println("2 - Déjà inscrit");
				System.out.println("0 - Quitter");

				int choice = scanner.nextInt();
				scanner.nextLine();

				if (choice == 0) {
					System.out.println("Au revoir !");
					break;
				}

				// INSCRIPTION
				if (choice == 1) {

					System.out.println("\n=== Inscription ===");

					System.out.print("Non* : ");
					String nom = scanner.nextLine();

					System.out.print("Prénom* : ");
					String prenom = scanner.nextLine();

					System.out.print("NSS (optionnel) : ");
					String nss = scanner.nextLine();
					if (nss.isBlank()) {
						nss = null;
					}

					System.out.print("Email* : ");
					String email = scanner.nextLine();

					String password;
					String confirmPassword;
					while (true) {
						System.out.print("Mot de passe* (min 6 caractères, 1 lettre, 1 chiffre) : ");
						password = scanner.nextLine();
						// longueur
						if (password.length() < 6) {
							System.out.println("Le mot de passe doit contenir au moins 6 caractères.");
							continue;
						}
						// lettre
						if (!password.matches(".*[A-Za-z].*")) {
							System.out.println("Le mot de passe doit contenir au moins une lettre.");
							continue;
						}
						// chiffre
						if (!password.matches(".*\\d.*")) {
							System.out.println("Le mot de passe doit contenir au moins un chiffre.");
							continue;
						}
						System.out.print("Confirmer le mot de passe : ");
						confirmPassword = scanner.nextLine();
						if (!password.equals(confirmPassword)) {
							System.out.println("Les mots de passe ne correspondent pas.");
							continue;
						}
						break;
					}

					try {
						var patient = patientService.createPatient(email, password, nom, prenom, nss );
						System.out.println("\nInscription réussie !");
						System.out.println("Votre identifiant : " + patient.getReference());
						launchDiagnostic(scanner, diagnosisService, patient.getId());

					} catch (Exception e) {
						System.out.println("Erreur : " + e.getMessage());
					}
				}

				// DEJÀ INSCRIT
				if (choice == 2) {

					System.out.println("\n=== Connexion ===");
					System.out.print("Identifiant patient (ex: PAT-XXXX) : ");
					String reference = scanner.nextLine().toUpperCase();
					System.out.print("Mot de passe : ");
					String password = scanner.nextLine();

					var patientOpt = patientRepository.findByReference(reference);
					if (patientOpt.isEmpty()) {
						System.out.println("Identifiant incorrect.");
						continue;
					}

					var patient = patientOpt.get();
					if (!passwordEncoder.matches(password, patient.getUser().getPassword())) {
						System.out.println("Mot de passe incorrect.");
						continue;
					}
					System.out.println("Connexion réussie !");

					while (true) {
						System.out.println("\nQue souhaitez-vous faire ?");
						System.out.println("1 - Commencer un diagnostic");
						System.out.println("2 - Consulter mes diagnostics");
						System.out.println("0 - Retour");
						int subChoice = scanner.nextInt();
						scanner.nextLine();

						if (subChoice == 0) {
							break;
						}

						if (subChoice == 1) {
							launchDiagnostic(scanner, diagnosisService, patient.getId());
							System.out.println("\nVous pouvez consulter votre diagnostic à tout moment");
							System.out.println("en vous connectant avec votre identifiant : " + patient.getReference());
						}

						if (subChoice == 2) {
							var diagnostics = diagnosisService.getDiagnosticsByPatient(patient.getId());
							if (diagnostics.isEmpty()) {
								System.out.println("Aucun diagnostic trouvé.");
							} else {
								System.out.println("\n=== Vos diagnostics ===");
								diagnostics.forEach(d -> {
									System.out.println("----------------------");
									System.out.println("Index : " + d.getHealthIndex());
									System.out.println("Unités : " + d.getMedicalUnits());
									String formattedDate = 	d.getCreatedAt().format(formatter);
									System.out.println("Date : " + formattedDate);
								});
							}
						}
					}
				}
			}
		};

	}

	private void launchDiagnostic(Scanner scanner, DiagnosisService diagnosisService, Long patientId) {
		System.out.println("\n=== Diagnostic ===");
		System.out.print("Entrez votre index de santé : ");
		int index = scanner.nextInt();
		scanner.nextLine();
		try {
			var result = diagnosisService.analyze(index, patientId);

			System.out.println("\nRésultat :");
			System.out.println("Index : " + result.getHealthIndex());
			if (result.getMedicalUnits().contains("GENERAL")) {
				System.out.println("Aucune pathologie détectée.");
				System.out.println("Orientation : Médecine générale.");
			} else {
				System.out.println("Unités : " + result.getMedicalUnits());
			}
			String formattedDate = result.getCreatedAt().format(formatter);
			System.out.println("Date : " + formattedDate);

		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}

	}


}
