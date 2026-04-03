# Indice Santé

## Contexte

Indice Santé est un système d’auto-diagnostic automatisé intégré à une cabine médicale.
Il analyse les données biométriques du patient à l’aide de capteurs et fournit un indicateur synthétique appelé "indice de santé", permettant une évaluation rapide et une orientation vers les unités médicales adaptées.

---

## Objectif

Développer un backend capable de :

* analyser un indice de santé
* déterminer les pathologies associées
* orienter le patient vers les unités médicales
* stocker l’historique des diagnostics
* exposer une API REST propre et sécurisée

---

## Stack Technique

* Java 21
* Spring Boot
* PostgreSQL
* Docker
* JPA / Hibernate
* Swagger (OpenAPI)
* JUnit

---

## Règles métier

* Multiple de 3 → Pathologie cardiaque → Cardiologie
* Multiple de 5 → Fracture → Traumatologie
* Multiple de 3 et 5 → Les deux

Le système a été étendu avec d’autres pathologies pour enrichir la simulation.

---

## Architecture

Le projet suit une architecture en couches :

* controller : exposition API REST
* service : logique métier
* repository : accès aux données
* model : entités JPA
* dto : objets d’échange API
* exception : gestion centralisée des erreurs

---

## Modélisation

Le système repose sur une séparation claire des responsabilités :

* User : authentification (email, password, role)
* Patient : données métier
* Diagnosis : résultat d’analyse
* MedicalUnit : orientation médicale

---

## Sécurité

* validation des entrées côté service
* exceptions personnalisées
* séparation Entity / DTO
* non-exposition des données sensibles (password)

---

## API REST

### Users

* GET /users → liste des utilisateurs
* GET /users/{id} → détail utilisateur

---

### Patients

* POST /patients → créer un patient
* GET /patients → liste des patients
* GET /patients/{id} → détail patient

---

### Diagnostic

* POST /diagnostic → créer un diagnostic
* GET /diagnostic/{patientId} → historique des diagnostics (tri décroissant)
* GET /diagnostic/{patientId}/latest → dernier diagnostic

---

## Exemple de requête

### POST /diagnostic

```json
{
  "patientId": 1,
  "healthIndex": 15
}
```

---

## Exemple de réponse

```json
{
  "healthIndex": 15,
  "medicalUnits": ["CARDIOLOGIE", "TRAUMATOLOGIE"],
  "createdAt": "2026-04-03T14:10:00"
}
```

---

## Tests

Les tests couvrent :

* logique métier (modulo 3 / 5)
* cas limites
* validation des entrées
* gestion des erreurs

---

## Lancement du projet

### Lancer la base de données

```bash
docker-compose up -d
```

### Lancer l'application

```bash
./mvnw spring-boot:run
```

---

## Documentation API

Swagger est disponible à :

http://localhost:8080/swagger-ui.html

---

## Améliorations possibles

* hash des mots de passe (BCrypt)
* pagination des résultats
* authentification JWT
* tests d’intégration

---


