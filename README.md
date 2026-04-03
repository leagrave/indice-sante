# indice-sante
## Contexte
Indice Santé est un système d’auto-diagnostic automatisé intégré à une cabine médicale. Il analyse les données biométriques du patient à l’aide de capteurs et fournit un indicateur synthétique appelé “indice de santé”, permettant une évaluation rapide et une orientation vers la ou les unités médicales concernées.

## Objectif
Développer un backend capable de :

- analyser un index de santé
- déterminer les pathologies associées
- orienter le patient
- stocker les diagnostics
- garantir la sécurité des données

## Stack Technique

- Java 21
- Spring Boot
- PostgreSQL
- Docker
- JPA / Hibernate
- JUnit (tests)

## Régles métier

- Multiple de 3 → Cardiologie
- Multiple de 5 → Traumatologie
- Multiple de 3 et 5 → Les deux

## Architecture

- controller : exposition API REST
- service : logique métier
- model : entités métier
- repository : accès aux données
- security : validation et contrôle des entrées
- exception : gestion centralisée des erreurs

## Modélisation
Le système repose sur une séparation claire entre :

- User (authentification)
- Patient / Praticien (métier)
- Diagnostic (résultat médical)

## Sécurité

- Validation stricte des entrées
- Gestion des erreurs via exceptions personnalisées
- Données sensibles (NSS) identifiées
- Architecture favorisant la séparation des responsabilités

## Lancement du projet

### Lancer la base de données
    `docker-compose up -d`
### Lancer l'application
    `mvn spring-boot:run`

## Tests

Les tests couvrent :

- logique métier (modulo 3 / 5)
- cas limites
- validation des entrées
- gestion des erreurs

