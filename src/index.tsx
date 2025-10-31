import RndroidAdvanceBattery from './NativeRndroidAdvanceBattery';

export const getLevel = () => RndroidAdvanceBattery.getLevel();
export const getStatus = () => RndroidAdvanceBattery.getStatus();
export const getHealth = () => RndroidAdvanceBattery.getHealth();
export const getTemperature = () => RndroidAdvanceBattery.getTemperature();
export const getTechnology = () => RndroidAdvanceBattery.getTechnology();
export const isCharging = () => RndroidAdvanceBattery.isCharging();
export const getCurrent = () => RndroidAdvanceBattery.getCurrent();
export const getVoltage = () => RndroidAdvanceBattery.getVoltage();
export const getWattage = () => RndroidAdvanceBattery.getWattage();

const Battery = {
    getLevel,
    getStatus,
    getHealth,
    getTemperature,
    getTechnology,
    isCharging,
    getCurrent,
    getVoltage,
    getWattage,
};

export default Battery;
