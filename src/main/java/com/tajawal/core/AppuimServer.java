package com.tajawal.core;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class AppuimServer {
    private final static Logger LOGGER = Logger.getLogger(AppuimServer.class);

    private AppiumDriverLocalService appiumServer;
    private static String ipAddress = "127.0.0.1";

    public URL startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress(ipAddress);
        Integer availablePort = getAvailablePort();
        builder.usingPort(availablePort);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);

        appiumServer = AppiumDriverLocalService.buildService(builder);
        appiumServer.clearOutPutStreams();
        LOGGER.info("Start appium");
        appiumServer.start();
        try {
            return new URL("http://" + ipAddress + ":" + availablePort + "/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException("cant create url", e);
        }
    }

    public void stopServer() {
        if (appiumServer != null) {
            LOGGER.info("Stop appium");
            appiumServer.stop();
            appiumServer = null;
        }
    }

    private Integer getAvailablePort() {
        for (int port = 4723; port < 7999; port += 2) {
            if (isPortAvailable(port)) {
                return port;
            }
        }

        throw new RuntimeException("port not found");
    }

    private boolean isPortAvailable(int port) {
        Socket s;

        try {
            s = new Socket(ipAddress, port);
            try {
                s.close();
            } catch (IOException e) {
                throw new RuntimeException("This should not have happened", e);
            }
        } catch (IOException e) {
            return true;
        }

        return false;
    }
}
