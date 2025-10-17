# Projet 3 - OpenClassrooms Java/Angular

## Description

Projet de développement d'une API REST avec Java Spring Boot et authentification JWT.

## Prérequis

- Java 21
- Maven
- MySQL (Docker)

## Lancement de l'application

### Lancer la BDD

```bash
cd infra
docker compose up -d
```

### Démarrer l'API

```bash
./mvnw spring-boot:run
```

L'API sera accessible sur `http://localhost:8080/api`

## Swagger

Un swagger est disponible sur `http://http://localhost:8080/api/swagger-ui/index.html`

Renseignez le schéma OpenAPI `/api/api-docs`

## Endpoints principaux

### Authentification

**Inscription**

```
POST /auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "motdepasse"
}
```

**Connexion**

```
POST /auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "motdepasse"
}
```

Réponse : `{ "token": "eyJhbGc..." }`

### Locations

Tous les endpoints de locations nécessitent un token JWT dans le header :

```
Authorization: Bearer {token}
```

**Créer une location**

```
POST /rentals
Content-Type: multipart/form-data

name: Appartement Paris
surface: 50
price: 1200
picture: [fichier image]
description: Bel appartement lumineux
```

**Lister les locations**

```
GET /rentals
```

**Détail d'une location**

```
GET /rentals/{id}
```

**Modifier une location**

```
PUT /rentals/{id}
Content-Type: application/json

{
  "name": "Appartement Paris",
  "surface": 50,
  "price": 1200,
  "description": "Bel appartement lumineux"
}
```

### Messages

**Envoyer un message**

```
POST /messages
Content-Type: application/json
Authorization: Bearer {token}

{
  "message": "Je suis intéressé",
  "userId": 1,
  "rentalId": 1
}
```

## Stockage des images

Les images uploadées sont stockées dans `src/main/resources/uploads/` et accessibles via :

```
GET /uploads/{filename}
```
