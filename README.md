# Grid configuration

(depuis le dossier grid/)  
Démarrage du Hub :  
java -jar selenium-server-4.0.0-beta-1.jar hub

Démarrage des Nodes :  
On aura ainsi un Node qui éxécute les tests qui demandent le driver firefox, et l'autre qui fait la même chose sur chrome

java -jar selenium-server-4.0.0-beta-1.jar node --publish-events "tcp://localhost:4442" --subscribe-events "tcp://localhost:4443"
java -jar selenium-server-4.0.0-beta-1.jar node --port 5556 --publish-events "tcp://localhost:4442" --subscribe-events "tcp://localhost:4443"

