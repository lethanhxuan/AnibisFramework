package com.nvg.anibis.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextScanner {
//	private final Path fFilePath;
	private final static Charset ENCODING = StandardCharsets.UTF_8;

//	private static void log(Object aObject) {
//		System.out.println(String.valueOf(aObject));
//	}

	/** Template method that calls {@link #processLine(String)}. */
	public static String getSerial(String filename) throws IOException {
		String result;
		try (Scanner scanner = new Scanner(Paths.get(filename), ENCODING.name())) {
			while (scanner.hasNextLine()) {
				result = scanner.nextLine();
				if(result != null && result.length()>=40){
					return result;
				} else continue;
			}		
			return null;
		}
	}
	
	public static String getAppPackage(String appPath) throws IOException{
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("/usr/local/bin/aapt dump badging "+appPath);
		BufferedReader stdInput = new BufferedReader(new 
			     InputStreamReader(proc.getInputStream()));
		String s = null;
		while((s = stdInput.readLine()) != null){
			if(s.contains("package: name=")){
				return s.split("'")[1];
			}
		}
		return null;

	}

}
