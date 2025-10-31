package com.rndroidadvancebattery

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.facebook.react.bridge.Promise

@ReactModule(name = RndroidAdvanceBatteryModule.NAME)
class RndroidAdvanceBatteryModule(reactContext: ReactApplicationContext) :
    NativeRndroidAdvanceBatterySpec(reactContext) {

    override fun getName(): String {
        return NAME
    }

    private val batteryManager: BatteryManager by lazy {
        reactApplicationContext.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    }

    private fun getBatteryIntent(): Intent? {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        return reactApplicationContext.registerReceiver(null, filter)
    }

    override fun getLevel(promise: Promise) {
        try {
            val batteryStatus = getBatteryIntent()
            val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
            val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
            val batteryPct = if (level >= 0 && scale > 0) (level / scale.toFloat()) * 100 else -1f
            promise.resolve(batteryPct)
        } catch (e: Exception) {
            promise.reject("BATTERY_LEVEL_ERROR", e)
        }
    }

    override fun isCharging(promise: Promise) {
        try {
            val batteryStatus = getBatteryIntent()
            val status = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val charging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL
            promise.resolve(charging)
        } catch (e: Exception) {
            promise.reject("BATTERY_CHARGING_ERROR", e)
        }
    }

    override fun getStatus(promise: Promise) {
        try {
            val status = getBatteryIntent()?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val statusString = when (status) {
                BatteryManager.BATTERY_STATUS_CHARGING -> "charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> "discharging"
                BatteryManager.BATTERY_STATUS_FULL -> "full"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "not_charging"
                else -> "unknown"
            }
            promise.resolve(statusString)
        } catch (e: Exception) {
            promise.reject("BATTERY_STATUS_ERROR", e)
        }
    }

    override fun getHealth(promise: Promise) {
        try {
            val health = getBatteryIntent()?.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)
            val healthString = when (health) {
                BatteryManager.BATTERY_HEALTH_GOOD -> "good"
                BatteryManager.BATTERY_HEALTH_OVERHEAT -> "overheat"
                BatteryManager.BATTERY_HEALTH_DEAD -> "dead"
                BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "over_voltage"
                BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "failure"
                else -> "unknown"
            }
            promise.resolve(healthString)
        } catch (e: Exception) {
            promise.reject("BATTERY_HEALTH_ERROR", e)
        }
    }

    override fun getTemperature(promise: Promise) {
        try {
            val temp = getBatteryIntent()?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) ?: -1
            val temperatureC = temp / 10.0 // Android returns tenths of a degree
            promise.resolve(temperatureC)
        } catch (e: Exception) {
            promise.reject("BATTERY_TEMPERATURE_ERROR", e)
        }
    }

    override fun getTechnology(promise: Promise) {
        try {
            val tech = getBatteryIntent()?.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY) ?: "unknown"
            promise.resolve(tech)
        } catch (e: Exception) {
            promise.reject("BATTERY_TECH_ERROR", e)
        }
    }
    
    override fun getCurrent(promise: Promise) {
        try {
            val currentMicroA = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)
            val currentMilliA = currentMicroA / 1000.0
            promise.resolve(currentMilliA)
        } catch (e: Exception) {
            promise.reject("BATTERY_CURRENT_ERROR", "Failed to get battery current", e)
        }
    }

    override fun getVoltage(promise: Promise) {
        try {
            val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val batteryStatus: Intent? = reactApplicationContext.registerReceiver(null, iFilter)
            val voltage = batteryStatus?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) ?: -1
            promise.resolve(voltage)
        } catch (e: Exception) {
            promise.reject("BATTERY_VOLTAGE_ERROR", "Failed to get battery voltage", e)
        }
    }

    override fun getWattage(promise: Promise) {
        try {
            val currentMicroA = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)
            val currentMilliA = currentMicroA / 1000.0 // mA

            val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val batteryStatus = reactApplicationContext.registerReceiver(null, filter)
            val voltageMilliV = batteryStatus?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) ?: -1

            // (mV × mA) / 1000 = mW, /1000 again → W
            val wattage = (voltageMilliV * currentMilliA) / 1000000.0

            promise.resolve(wattage)
        } catch (e: Exception) {
            promise.reject("BATTERY_WATTAGE_ERROR", "Failed to get battery wattage", e)
        }
    }

    companion object {
        const val NAME = "RndroidAdvanceBattery"
    }
}
