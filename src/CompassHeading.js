
import RNCompassHeading from './CompassHeadingNative';
/**
    Method for init the DeviceSensor. Return a promise, get resolved when the sensor is active
*/
async function initSensor() {
    await RNCompassHeading.initSensor();
    Promise.resolve(true);
}
/**
      Method for get the Distance in Degree from the true North. Return a promise, get resolved with a number (degree)
*/
async function getHeading() {
    return await RNCompassHeading.getHeading();
}

export default {
    initSensor,
    getHeading
}
