***Lancer wiremock avec docker ***

```
docker run -it --rm
    -p 8080:8080
    --name wiremock
    -v $PWD:/home/wiremock
    wiremock/wiremock:2.32.0
```


vérifie le mapping :
http://localhost:8080/__admin/mappings

Voir la liste de toutes les requêtes :
http://localhost:8080/__admin/requests

Ajouter un stub via curl

```
curl -X POST --data  '{                                                                                                                                                                       (base)  + ✱ ●  ~/w/b/d/wiremock
                       "request": {
                           "method": "GET",
                           "url": "/some/thing"
                       },
                       "response": {
                           "status": 200,
                           "body": "Hello world!",
                           "headers": {
                               "Content-Type": "text/plain"
                           }
                       }
                   }' http://localhost:8080/__admin/mappings
```
