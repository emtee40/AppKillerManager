package com.thelittlefireman.appkillermanager.managers;

import com.thelittlefireman.appkillermanager.devices.Asus;
import com.thelittlefireman.appkillermanager.devices.DeviceAbstract;
import com.thelittlefireman.appkillermanager.devices.DeviceBase;
import com.thelittlefireman.appkillermanager.devices.HTC;
import com.thelittlefireman.appkillermanager.devices.Huawei;
import com.thelittlefireman.appkillermanager.devices.Letv;
import com.thelittlefireman.appkillermanager.devices.Meizu;
import com.thelittlefireman.appkillermanager.devices.OnePlus;
import com.thelittlefireman.appkillermanager.devices.Samsung;
import com.thelittlefireman.appkillermanager.devices.Xiaomi;
import com.thelittlefireman.appkillermanager.devices.ZTE;
import com.thelittlefireman.appkillermanager.utils.SystemUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class DevicesManager {

    private static List<DeviceAbstract> deviceBaseList = new ArrayList<>(Arrays.asList(
            new Asus(),
            new Huawei(),
            new Letv(),
            new Meizu(),
            new OnePlus(),
            new HTC(),
            new Samsung(),
            new Xiaomi(),
            new ZTE()));

    public static DeviceBase getDevice() {
        List<DeviceBase> currentDeviceBase = new ArrayList<>();
        for (DeviceBase deviceBase : deviceBaseList) {
            if (deviceBase.isThatRom()) {
                currentDeviceBase.add(deviceBase);
            }
        }
        if (currentDeviceBase.size() > 1) {
            StringBuilder logDevices = new StringBuilder();
            for (DeviceBase deviceBase : currentDeviceBase) {
                logDevices.append(deviceBase.getDeviceManufacturer());
            }
            Timber.tag(DevicesManager.class.getName()).e("MORE THAN ONE CORRESPONDING:" + logDevices + "|" +
                    SystemUtils.getDefaultDebugInformation());
        }

        if (currentDeviceBase.size() > 0) {
            return currentDeviceBase.get(0);
        } else {
            return null;
        }
    }
}
