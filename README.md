[![en](https://img.shields.io/badge/lang-en-ab4b52.svg)](https://github.com/tlebigre/henHouseBackendApi/blob/main/README.md)
[![fr](https://img.shields.io/badge/lang-fr-318ce7.svg)](https://github.com/tlebigre/henHouseBackendApi/blob/main/README.fr.md)

> :warning: ***This api work only if [henHouseBoardBackendApi](https://github.com/tlebigre/henHouseBoardBackendApi) run !***

# API Reference
## Camera
### Save a camera
```http
POST /saveCamera
```
| Parameter | Type | Description |
| :-------- | :------- | :------------------------- |
|  `simpleCameraDto`  |  `SimpleCameraDto`  |  **Required**. Camera to save |

***SimpleCameraDto class*** (all is **Required**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `name`  |  `int`  |  Name |
|  `url`  |  `int`  |  URL |
### Edit a camera
```http
POST /editCamera
```
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `cameraDto`  |  `CameraDto`  |  **Required**. Camera to edit |

***CameraDto class*** (all is **Required**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `id`  |  `int`  |  Id |
|  `name`  |  `int`  |  Name |
|  `url`  |  `int`  |  URL |

### Returns a camera (CameraDto) by its id
```http
GET /getCamera/{id}
```
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `id`  |  `int`  |  **Required**. Id |

### Returns all cameras (list of CameraDto)
```http
GET /getAllCamera
```
### Remove a camera by its id
```http
DELETE /removeCamera/{id}
```
| Parameter | Type | Description |
| :-------- | :------- | :------------------------- |
|  `id`  |  `int`  |  **Required**. Id|

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
