DOCUMENTATION FONCTIONNELLE DE LA PARTIE BACK-END DU PROJET E-BANK

ARCHITECTURE DE NOTRE SYSTEME DESTRIBUEE :
![image](https://github.com/Aymane-Chakir/bank-account/assets/134889823/9d0f23d1-b68b-48a6-b6bf-9d9aff0b9d20)
Mots clés :
Registre :  l’enregistrement des services chez Eureka service
Config : Récupération de la configuration 
Rest : Communication synchrone entre les systèmes distribué  
Http : Requête http

DIAGRAMME DE CLASSES UML 
![image](https://github.com/Aymane-Chakir/bank-account/assets/134889823/bdf0476f-4b9d-4184-8f2a-978783a61baf)

STRICTURE DU PROJET :
![image](https://github.com/Aymane-Chakir/bank-account/assets/134889823/fc12eafc-cbd0-4440-9b7d-c8257cdf6f9d)

IMPLEMENTATION DES MICRO-SERVICE :
1-	ST : DISCOVRY-SERVICE ARCHITECTURE :

Les dépendances utilisent :
•	Eureka Server
•	Spring Boot Actuator

L’activation de Eureka server :
Il suffit d’ajouter l’annotation @EnableEurikaServer dans DiscoveryServiceApplication

![image](https://github.com/Aymane-Chakir/bank-account/assets/134889823/f62552e9-bcf6-45a4-b20b-201fe9ddf26b)




2-	ST : CONFIGURATION-SERVICE

Les dépendances utilisent :
•	Eureka client
•	Spring Cloud Config server
•	Spring boot Actuator 
L’activation de Eureka server :
Il suffit d’ajouter l’annotation @EnableEurikaServer dans DiscoveryServiceApplication

Création d’un repository coté serveur :
![image](https://github.com/Aymane-Chakir/bank-account/assets/134889823/3c3d74cd-8537-4dd2-a29a-98f5a45cee56)



3-	 ST : GATEWAY-SERVICE
Les dépendances utilisent :
•	Spring Cloud Routing : gateway
•	Eureka client
•	Spring Boot Actuator

4-	CUSTOMER-SERVICE

Les dépendances utilisent :
•	Spring data jpa
•	Lombok
•	Spring web 
•	Eureka client
•	H2 Console data base
•	Mysql db
•	OAuth2
•	Spring boot actuator
•	Spring Cloud Config : config client
•	Swagger

Structure du customer service:
![image](https://github.com/Aymane-Chakir/bank-account/assets/134889823/468531ee-bb8a-427a-8d54-403ea2034b22)


Client package:
-	RestControllet client faire l’appel à des méthode d’autre service via opefeing
-	On a utilisé aussi RESILIEN4J le circuit breaker pour gérer les faut de tolérance
Config package 
-	Contiens une classe permet a récupérer la configuration globale par la spécification des préfix.
Entité package :
-	C’est une classe JPA contient des privés attribue, cette class correspond à une table dans la base de données.
Enums package : 
-	C’est une classe de types enumérateurs contient des strings ou des chiffres.
Exception package :
-	Classe qui hérité d’une autre classe parant « Exception », pour gérer les messages d’erreurs.
DTO Package :
-	Ce modèle est souvent utilisé dans les architectures a plusieurs niveaux pour encapsuler des données et la Transfer d’une manier efficace et sécurisée
Mapper Package :
-	Utiliser pour convertir des données d’un forme a autre
-	Convertir un objet De type Entité a un objet de type Réponse
-	Convertir un objet de type Requête a un objet de type entité
Model Package :
-	Model de l’entité Account pour le utilisé par Openfeing.
Repository Package :
-	Création d’une interface qui hérité d’une classe JPA 
-	Création des méthodes ou des Query HQL pour récupère le data à travers le JDBC

Service Package :
-	Création d’une interface dans le quelle on définit les fonctionnalité de l’entité
-	Implémentation de l’interface et écrire du code métier de la couche service

Web Package : 
-	Création de la couche web 
-	RestController
-	Gérer le mapping objet relationnel 
-	Gere le requête http
Sécurité Package :
-	Utiliser le système de sécurité stateless.
-	Désactiver le CSRF
-	Gérer l’authentification et les autorisation
-	Création de JWT

