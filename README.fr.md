[![en](https://img.shields.io/badge/lang-en-ab4b52.svg)](https://github.com/tlebigre/henHouseBackendApi/blob/main/README.md)
[![fr](https://img.shields.io/badge/lang-fr-318ce7.svg)](https://github.com/tlebigre/henHouseBackendApi/blob/main/README.fr.md)

> :warning: ***Cette api fonctionne seulement si [henHouseBoardBackendApi](https://github.com/tlebigre/henHouseBoardBackendApi) fonctionne !***

# API Reference
## Camera
### Enregistrer une caméra
```http
POST /saveCamera
```
| Parameter | Type | Description |
| :-------- | :------- | :------------------------- |
|  `simpleCameraDto`  |  `SimpleCameraDto`  |  **Requis**. Caméra à enregistrer |

***SimpleCameraDto class*** (tout est **Requis**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `name`  |  `int`  |  Nom |
|  `url`  |  `int`  |  URL |
### Modifier une caméra
```http
POST /editCamera
```
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `cameraDto`  |  `CameraDto`  |  **Requis**. Caméra à modifier |

***CameraDto class*** (Tout est **Requis**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `id`  |  `int`  |  Identifiant |
|  `name`  |  `int`  |  Nom |
|  `url`  |  `int`  |  URL |

### Retourne une caméra (CameraDto) par son identifiant
```http
GET /getCamera/{id}
```
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `id`  |  `int`  |  **Required**. Identifiant |

### Retourne toutes les caméras (liste de CameraDto)
```http
GET /getAllCamera
```
### Supprime une caméra par son identifiant
```http
DELETE /removeCamera/{id}
```
| Parameter | Type | Description |
| :-------- | :------- | :------------------------- |
|  `id`  |  `int`  |  **Required**. Identifiant|

## Henhouse
### Save hen house parameters
```http
POST /saveHenHouse
```
| Parameter | Type | Description |
| :-------- | :------- | :------------------------- |
|  `henHouseDto`  |  `HenHouseDto `  |  **Required**. Hen house parameters to save |

***HenHouseDto class*** (all is **Required**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `getUpMorning`  |  `boolean` | If true : "get up" feature activated |
|  `stopLimits`  |  `boolean` | If true : engine no longer powered when top or bottom limit are reached |
|  `stopEngine`  |  `boolean` | If true : engine no longer powered when it doesn't work |
|  `hourOpening`  |  `String` | Opening hour |
|  `hourOpeningMax`  |  `String` | Max opening hour (in case of power failure)|
|  `state`  |  `int` | Engine state |
|  `topLimit`  |  `int` | Engine top limit |
|  `bottomLimit`  |  `int` | Engine bottom limit |
|  `speed`  |  `int` | Engine speed |
|  `date`  |  `String` | Date (format: dd/MM/yyyy) |
|  `time`  |  `String` | Time (format: HH:mm) |

### Returns hen house parameters (HenHouseDto)
```http
GET /getHenHouse
```

### Returns date time (DateTimeDto)
```http
GET /getDateTime
```
***DateTimeDto class***
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `date`  |  `String ` | Date (format: dd/MM/yyyy) |
|  `time`  |  `String ` | Time (format: HH:mm) |

### Save engine state
```http
POST /saveState
```
***StateDto class*** (all is **Required**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `state`  |  `int ` | Engine state |

### Returns engine state (StateDto)
```http
GET /getState
```
## Opening
### Save last opening
```http
POST /saveLastOpening
```
***LastOpeningDto class*** (all is **Required**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `date`  |  `String ` | Date (format: dd/MM/yyyy) |

### Returns last opening (LastOpeningDto)
```http
GET /getLastOpening
```

### Open
```http
GET /open
```

### Close
```http
GET /close
```

