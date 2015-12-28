package com.nvg.anibis.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

//Start or Stop appium server programmatically
public class AppiumServer {

	
	public static AppiumDriverLocalService service; 

	// Starting the Appium Server
	public void start() throws IOException, InterruptedException {
		Map<String, String> env = new HashMap<>();
		env.put("ANDROID_HOME", "/usr/local/opt/android-sdk");
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingAnyFreePort()
				.withArgument(GeneralServerFlag.LOG_LEVEL, "error").withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.usingDriverExecutable(new File("/usr/local/opt/node/bin/node"))
				.withEnvironment(env)
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/bin/appium.js"))
				.withLogFile(new File(System.getProperty("user.dir") + "/log.txt")));
		
		service.start();

	}

	// Stopping the Appium Server
	public void stop() throws IOException {
		service.stop();
	}

}
