# TapEm
Simple, two-player Android game - Tap the squares faster than your opponent

TapEm is an Android app I am creating for my senior project at UNG. More information will be added as the project progresses. 

## Build
To build this app in Android Studio, simply clone this repository and import it as a new Android Studio project. You may then compile and run it on either an emulator or physical device.

### Server Notice
For the time being, the server for the game is not always active. If you would like to create your own server for personal use of this app, use the `server.js` file included in the root directory of the repository. You must change `HOST` and `PORT` variables in the `server.js` file, and then update the Android application accordingly in `UpdateTask.java`. Then, simply navigate to directory containing `server.js` and run:

```
$ node server.js
```

The server should begin running, and you will then be able to connect to it via the Android application.
