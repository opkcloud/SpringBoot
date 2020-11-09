package com.opkcloud.util;

import lombok.extern.slf4j.Slf4j;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

@Slf4j
public class NetUtil {

    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                // 过来环回、虚拟以及虚拟机地址
                if (netInterface.isLoopback() || netInterface.isVirtual()
                        || !netInterface.isUp() || netInterface.getDisplayName().contains("Virtual")) {
                    continue;
                }

                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip instanceof Inet4Address) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            log.warn("get ip address take error.", ex);
        }
        return "";
    }
}
