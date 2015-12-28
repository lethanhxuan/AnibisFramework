package com.nvg.anibis.support;

public final class Condition {
	static String platform;
	static String device;
	
	public Condition(String platform, String device) {
		Condition.platform = platform;
		Condition.device = device;
	}

	public boolean RunningOnAndroid(){
		return platform.startsWith("android");
	}
	
	public boolean RunningOnIOS(){
		return platform.startsWith("ios");
	}
	
	public boolean RunningOniPhone(){
		return device.startsWith("iPhone");
	}
	
	public boolean RunningOniPad(){
		return device.startsWith("iPad");
	}
}
