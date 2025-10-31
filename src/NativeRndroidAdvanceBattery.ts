import { TurboModuleRegistry, type TurboModule } from 'react-native';

export interface Spec extends TurboModule {
    getLevel(): Promise<number>;
    getStatus(): Promise<string>;
    getHealth(): Promise<string>;
    getTemperature(): Promise<number>;
    getTechnology(): Promise<string>;
    isCharging(): Promise<boolean>;
    getCurrent(): Promise<number>;
    getVoltage(): Promise<number>;
    getWattage(): Promise<number>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RndroidAdvanceBattery');
