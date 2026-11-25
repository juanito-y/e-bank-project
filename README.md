# E-Bank Project – Partie 1 : Micro-services backend

## 1. Objectif

Mettre en place le backend d'une application bancaire en architecture micro-services : gestion des clients, gestion des comptes bancaires, communication inter-services, tolérance aux pannes, découverte de services et point d'entrée unique.

## 2. Architecture

| Service | Port | Rôle |
|---|---|---|
| `discovery-service` | 8761 | Annuaire des services (Eureka Server) |
| `customer-service` | 8081 | Gestion des clients |
| `ebank-service` | 8082 | Gestion des comptes bancaires |
| `gateway-service` | 8888 | Point d'entrée unique (Spring Cloud Gateway) |

Les services s'enregistrent dans Eureka. La gateway route les requêtes vers `customer-service` et `ebank-service` grâce à Eureka. `ebank-service` appelle `customer-service` pour récupérer le client associé à chaque compte.

## 3. Technologies

Java 21, Maven, Spring Boot 4.2.0, Spring Data JPA, H2, Spring Cloud 2026.0.0 (Eureka, OpenFeign, Gateway, Resilience4J), Springdoc OpenAPI (Swagger-UI), Lombok.

## 4. Réalisations

- **Customer-Service** : entité `Customer`, repository JPA, API REST (liste, consultation par id, création), données de test au démarrage.
- **EBank-Service** : entité `BankAccount` (type CURRENT ou SAVING), API REST de consultation des comptes, enrichis avec les informations du client.
- **OpenFeign** : l'interface `CustomerRestClient` permet à `ebank-service` d'appeler `customer-service` par son nom, sans connaître son adresse.
- **Circuit Breaker (Resilience4J)** : si `customer-service` est indisponible, un fallback renvoie un client « Not available » au lieu de faire échouer toute la requête.
- **Discovery Service** : serveur Eureka.
- **Gateway** : routage dynamique basé sur le nom des services enregistrés.

## 5. Lancement

Prérequis : JDK 21 et Maven. Démarrer dans cet ordre : `discovery-service`, `customer-service`, `ebank-service`, `gateway-service`.

```bash
mvn spring-boot:run
```

(à lancer dans le dossier de chaque service). Attendre environ 30 secondes, puis vérifier http://localhost:8761.

## 6. Endpoints (via la gateway)

| Méthode | URL | Description |
|---|---|---|
| GET | `/customer-service/api/customers` | Liste des clients |
| GET | `/customer-service/api/customers/{id}` | Un client |
| POST | `/customer-service/api/customers` | Créer un client |
| GET | `/ebank-service/api/accounts` | Liste des comptes avec leur client |
| GET | `/ebank-service/api/accounts/{id}` | Un compte |

Base URL : `http://localhost:8888`

Swagger-UI : http://localhost:8081/swagger-ui.html et http://localhost:8082/swagger-ui.html

## 7. Tests

Les services ont été testés avec Postman et Swagger-UI.

**Test du circuit breaker :**
1. Appeler `GET /ebank-service/api/accounts` : les comptes contiennent le vrai client.
2. Arrêter `customer-service`.
3. Rappeler la même URL : les comptes sont renvoyés avec « Not available » à la place du client.
4. Relancer `customer-service` : le fonctionnement redevient normal.

## 8. Conclusion

La partie 1 met en place une base micro-services complète : services métier, communication déclarative avec OpenFeign, résilience avec Resilience4J, découverte de services avec Eureka et point d'entrée unique avec la gateway.

## Auteur
yasmine ghissassi
