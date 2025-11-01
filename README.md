# 🔋 rndroid-advance-battery

An Android-only **React Native** package that provides detailed battery information such as **percentage, status, health, voltage, current, wattage, and more** — all through a simple API.

> ⚙️ Built as a Turbo Module for maximum performance.

---

## 📦 Installation

```bash
npm install rndroid-advance-battery
```

## 🚀 Usage

```js
// Option 1: Import the full module
import Battery from 'rndroid-advance-battery';

// Option 2: Import specific functions
import { getLevel, getStatus, getHealth } from 'rndroid-advance-battery';
```

Now you can use any of the following async methods:

### 🔋 getLevel()

```js
const level = await Battery.getLevel();
console.log(`Battery Level: ${level}%`);
```

Returns: Battery level (0–100).

### ⚡ getStatus()

```js
const status = await Battery.getStatus();
console.log(`Status: ${status}`);
```

Returns: "Charging", "Discharging", "Full", "Not Charging", or "Unknown".

### ❤️ getHealth()

```js
const health = await Battery.getHealth();
console.log(`Health: ${health}`);
```

Returns: Battery condition such as "Good", "Overheat", "Cold", etc.

### 🌡️ getTemperature()

```js
const temp = await Battery.getTemperature();
console.log(`Temperature: ${temp} °C`);
```

Returns: Current battery temperature.

### ⚙️ getTechnology()

```js
const tech = await Battery.getTechnology();
console.log(`Technology: ${tech}`);
```

Returns: Battery chemistry, e.g. "Li-ion".

### 🔌 isCharging()

```js
const charging = await Battery.isCharging();
console.log(charging ? 'Charging' : 'Not Charging');
```

Returns: true if charging, else false.

### ⚙️ getCurrent()

```js
const current = await Battery.getCurrent();
console.log(`Current: ${current} mA`);
```

Returns: Instantaneous current (mA). Positive when charging, negative when discharging.

### ⚙️ getVoltage()

```js
const voltage = await Battery.getVoltage();
console.log(`Voltage: ${voltage} mV`);
```

Returns: Current voltage in millivolts.

### ⚙️ getWattage()

```js
const wattage = await Battery.getWattage();
console.log(`Wattage: ${wattage} W`);
```

Returns: Real-time power draw in watts (calculated from voltage × current).

### 💾 getTotalCapacity()

```js
const total = await Battery.getTotalCapacity();
console.log(`Total Capacity: ${total} mAh`);
```

Returns: Estimated total design capacity (mAh).

### ⚡ getChargedCapacity()

```js
const charged = await Battery.getChargedCapacity();
console.log(`Charged Capacity: ${charged} mAh`);
```

Returns: Current charged capacity (mAh).

### 🧩 Example: Fetch All at Once

```js
async function logBatteryInfo() {
  const data = {
    level: await getLevel(),
    status: await getStatus(),
    health: await getHealth(),
    temperature: await getTemperature(),
    technology: await getTechnology(),
    isCharging: await isCharging(),
    current: await getCurrent(),
    voltage: await getVoltage(),
    wattage: await getWattage(),
    totalCapacity: await getTotalCapacity(),
    chargedCapacity: await getChargedCapacity(),
  };

  console.log('Battery Info:', data);
}

logBatteryInfo();
```

### 📘 Quick Reference

| Method               | Returns | Unit / Type | Description                |
| -------------------- | ------- | ----------- | -------------------------- |
| getLevel()           | number  | %           | Current battery level      |
| getStatus()          | string  | —           | Charging/discharging state |
| getHealth()          | string  | —           | Battery condition          |
| getTemperature()     | number  | °C          | Battery temperature        |
| getTechnology()      | string  | —           | Battery technology         |
| isCharging()         | boolean | —           | Whether charging or not    |
| getCurrent()         | number  | mA          | Current flow               |
| getVoltage()         | number  | mV          | Voltage                    |
| getWattage()         | number  | W           | Power draw                 |
| getTotalCapacity()   | number  | mAh         | Total design capacity      |
| getChargedCapacity() | number  | mAh         | Current charged capacity   |
