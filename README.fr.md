[![en](https://img.shields.io/badge/lang-en-ab4b52.svg)](https://github.com/tlebigre/henHouseBackendApi/blob/main/README.md)
[![fr](https://img.shields.io/badge/lang-fr-318ce7.svg)](https://github.com/tlebigre/henHouseBackendApi/blob/main/README.fr.md)

# Poulailler backend Api

> :warning: ***Cette api fonctionne seulement si [henHouseBoardBackendApi](https://github.com/tlebigre/henHouseBoardBackendApi) est en fonctionnement !***

Cette API permet :
* gérer l'ouverture du poulailler à une certaine heure (avec une horloge permanente).
* ouvrir/fermer la porte avec les boutons ou l'API.
* gérer le moteur (vitesse, limite haute, limite basse, état).
* gérer une liste de caméras (nom et URL).

## Lancer

Lancer le serveur de développement:

```bash
gradlew bootRun
```

## Construire

```bash
gradlew build
```

Pour créer un fichier war (dans *build/libs*):
```bash
gradlew bootWar
```


# API Reference
## Camera
### Enregistrer une caméra
```http
POST /camera
```
| Paramètre | Type | Description |
| :-------- | :------- | :------------------------- |
|  `simpleCameraDto`  |  `SimpleCameraDto`  |  **Requis**. Caméra à enregistrer |

***SimpleCameraDto class*** (tout est **Requis**)
| Paramètre | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `name`  |  `int`  |  Nom |
|  `url`  |  `int`  |  URL |
### Modifier une caméra
```http
PUT /camera
```
| Paramètre | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `cameraDto`  |  `CameraDto`  |  **Requis**. Caméra à modifier |

***CameraDto class*** (Tout est **Requis**)
| Paramètre | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `id`  |  `int`  |  Identifiant |
|  `name`  |  `int`  |  Nom |
|  `url`  |  `int`  |  URL |

### Retourne une caméra (CameraDto) par son identifiant
```http
GET /camera/{id}
```
| Paramètre | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `id`  |  `int`  |  **Requis**. Identifiant |

### Retourne toutes les caméras (liste de CameraDto)
```http
GET /cameras
```
### Supprime une caméra par son identifiant
```http
DELETE /camera/{id}
```
| Paramètre | Type | Description |
| :-------- | :------- | :------------------------- |
|  `id`  |  `int`  |  **Requis**. Identifiant|

## Poulailler
### Sauvegarde des paramètres du poulailler
```http
POST /henHouse
```
| Paramètre | Type | Description |
| :-------- | :------- | :------------------------- |
|  `henHouseDto`  |  `HenHouseDto `  |  **Requis**. Paramètres du poulailler à sauvegarder |

***HenHouseDto class*** (tout est **Requis**)
| Paramètre | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `getUpMorning`  |  `boolean` | Si vrai : Ouverture automatique activée |
|  `stopLimits`  |  `boolean` | Si vrai : Le moteur n'est plus alimenté lorsque la butée haute ou basse est atteinte|
|  `stopEngine`  |  `boolean` | Si vrai : Le moteur n'est plus alimenté lorsqu'il n'est pas en fonctionnement |
|  `hourOpening`  |  `String` | Heure d'ouverture |
|  `hourOpeningMax`  |  `String` | Heure d'ouverture maximale (en cas de coupure d'éléctricité)|
|  `state`  |  `int` | Etat/position du moteur |
|  `topLimit`  |  `int` | Limite haute du moteur |
|  `bottomLimit`  |  `int` | Limite basse du moteur |
|  `speed`  |  `int` | Vitesse du moteur |
|  `date`  |  `String` | Date (format: dd/MM/yyyy) |
|  `time`  |  `String` | Heure (format: HH:mm) |

### Retourne les paramètres du poulailler (HenHouseDto)
```http
GET /henHouse
```

### Retourne la date et l'heure (DateTimeDto)
```http
GET /dateTime
```
***DateTimeDto class***
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `date`  |  `String ` | Date (format: dd/MM/yyyy) |
|  `time`  |  `String ` | Heure (format: HH:mm) |

### Enregistre l'état/position du moteur
```http
POST /state
```
***StateDto class*** (tout est **Required**)
| Paramètre | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `state`  |  `int ` | Etat du moteur |

### Retourne l'état/position du moteur (StateDto)
```http
GET /state
```
## Ouverture
### Enregistre la dernière ouverture
```http
POST /lastOpening
```
***LastOpeningDto class*** (all is **Required**)
| Paramètre | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `date`  |  `String ` | Date (format: dd/MM/yyyy) |

### Retourne la dernière ouverture (LastOpeningDto)
```http
GET /lastOpening
```

### Ouvre
```http
GET /open
```

### Ferme
```http
GET /close
```

