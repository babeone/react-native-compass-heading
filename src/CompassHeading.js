
import RNCompassHeading from './CompassHeadingNative';

async function initSensor() {
    await RNCompassHeading.initSensor();
    Promise.resolve(true);
}

async function getHeading() {
    return await RNCompassHeading.getHeading();
}

export default {
    initSensor,
    getHeading
}
