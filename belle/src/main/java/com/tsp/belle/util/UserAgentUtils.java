package com.tsp.belle.util;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentUtils {
    public static String getDeviceType(String agent) {
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem(); // 操作系统信息
        eu.bitwalker.useragentutils.DeviceType deviceType = operatingSystem.getDeviceType(); // 设备类型
        switch (deviceType) {
            case COMPUTER:
                return "PC";
            case MOBILE: {
                return "MOBILE";
            }
            default:
                return "Unknown";
        }

    }
}
