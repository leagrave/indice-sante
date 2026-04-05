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
* exposer une API REST sécurisée avec authentification

---

## Stack Technique

* Java 21
* Spring Boot 3
* Spring Security
* JWT (JSON Web Token)
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

Le système a été enrichi avec plusieurs pathologies pour simuler un environnement médical réaliste.

---

## Architecture

Le projet suit une architecture en couches :

* controller : exposition API REST
* service : logique métier
* repository : accès aux données
* model : entités JPA
* dto : objets d’échange API
* security : configuration JWT et filtres
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

Le projet implémente une authentification moderne basée sur JWT :

* authentification via `/auth/login`
* génération d’un token JWT
* sécurisation des endpoints via filtre Spring Security
* accès protégé nécessitant un header `Authorization: Bearer <token>`

### Accès aux endpoints

| Endpoint         | Accès                      |
| ---------------- | -------------------------- |
| /auth/**         | public                     |
| POST /patients   | public (création initiale) |
| autres endpoints | protégés                   |

---

## Authentification
Lors du premier lancement de l’application, aucun utilisateur n’est présent en base de données. Il est donc nécessaire de créer un utilisateur (via la création d’un patient ou insertion en base) avant de pouvoir utiliser l’endpoint d’authentification.

### POST /auth/login

```json
{
  "email": "test@test.com",
  "password": "Test123"
}
```

### Réponse token JWT

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Swagger Authorize

```
Authorization: Bearer <eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...>
```

---

## API REST

### Users

* GET /users → liste des utilisateurs
* GET /users/{id} → détail utilisateur

---

### Patients

* POST /patients → créer un patient (public)
* GET /patients → liste des patients (auth requis)
* GET /patients/{id} → détail patient (auth requis)

---

### Diagnostic

* POST /diagnostic → créer un diagnostic
* GET /diagnostic/{patientId} → historique (tri décroissant)
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

## Swagger

Documentation disponible :

http://localhost:8080/swagger-ui/index.html

Swagger intègre l’authentification JWT via le bouton **Authorize**.

---

## Configuration JWT

Le secret JWT est externalisé via variable d’environnement.

### application.yml

```yaml
jwt:
  secret: ${JWT_SECRET:default-secret-dev}
```

### Variable d’environnement
>Il est important de faire cette commande avant le lancement du projet.

#### Windows

```bash
setx JWT_SECRET "your-super-secret-key"
```

#### Linux / Mac

```bash
export JWT_SECRET="your-super-secret-key"
```

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

## Tests

Les tests couvrent :

* logique métier (modulo)
* cas limites
* validation des entrées
* gestion des erreurs

### Lancer les tests

```bash
./mvnw test
```

---

## Améliorations possibles

* gestion des rôles (ADMIN / USER)
* refresh token
* pagination
* logs de sécurité
* monitoring
* front-end (Vue.js)

---
