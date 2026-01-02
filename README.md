[![en](https://img.shields.io/badge/lang-en-ab4b52.svg)](https://github.com/tlebigre/henHouseBackendApi/blob/main/README.md)
[![fr](https://img.shields.io/badge/lang-fr-318ce7.svg)](https://github.com/tlebigre/henHouseBackendApi/blob/main/README.fr.md)

# Hen house backend Api

> :warning: ***This api work only if [henHouseBoardBackendApi](https://github.com/tlebigre/henHouseBoardBackendApi) run !***

This API allows :
* manage the opening of hen house door at a certain time (with real-time clock).
* open/close the door with buttons or api.
* manage engine (speed, top limit, bottom limit, state).
* manage a camera (name and url) list. 

## Developing

Start a development server:

```bash
gradlew bootRun

```

## Building

```bash
gradlew build
```

To create war file (in *build/libs*):
```bash
gradlew bootWar
```

# API Reference
## Camera
### Save a camera
```http
POST /camera
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
PUT /camera
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
GET /camera/{id}
```
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `id`  |  `int`  |  **Required**. Id |

### Returns all cameras (list of CameraDto)
```http
GET /cameras
```
### Remove a camera by its id
```http
DELETE /camera/{id}
```
| Parameter | Type | Description |
| :-------- | :------- | :------------------------- |
|  `id`  |  `int`  |  **Required**. Id|

## Henhouse
### Save hen house parameters
```http
POST /henHouse
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
GET /henHouse
```

### Returns date time (DateTimeDto)
```http
GET /dateTime
```
***DateTimeDto class***
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `date`  |  `String ` | Date (format: dd/MM/yyyy) |
|  `time`  |  `String ` | Time (format: HH:mm) |

### Save engine state
```http
POST /state
```
***StateDto class*** (all is **Required**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `state`  |  `int ` | Engine state |

### Returns engine state (StateDto)
```http
GET /state
```
## Opening
### Save last opening
```http
POST /lastOpening
```
***LastOpeningDto class*** (all is **Required**)
| Parameter | Type | Description |
| :-------- | :------- | :-------------------------------- |
|  `date`  |  `String ` | Date (format: dd/MM/yyyy) |

### Returns last opening (LastOpeningDto)
```http
GET /lastOpening
```

### Open
```http
GET /open
```

### Close
```http
GET /close
```
