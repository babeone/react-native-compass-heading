
# react-native-compass-heading

## Work only for Android, no IOS module !!

## Getting started

`$ npm install react-native-compass-heading --save`

### Mostly automatic installation

`$ react-native link react-native-compass-heading`

### Manual installation


#### iOS -> NO VERSION FOR IOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-compass-heading` and add `RNCompassHeading.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNCompassHeading.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNCompassHeadingPackage;` to the imports at the top of the file
  - Add `new RNCompassHeadingPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-compass-heading'
  	project(':react-native-compass-heading').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-compass-heading/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-compass-heading')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNCompassHeading.sln` in `node_modules/react-native-compass-heading/windows/RNCompassHeading.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Compass.Heading.RNCompassHeading;` to the usings at the top of the file
  - Add `new RNCompassHeadingPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNCompassHeading from 'react-native-compass-heading';

await RNCompassHeading.initSensor(); //init the Device Sensor, it's async Method
let degree = await RNCompassHeading.getHeading(); //get the Degree, it's async and get resolved with a int (degree)


```
  
