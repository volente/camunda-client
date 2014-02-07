package com.rocksbook.camunda.client;

import java.io.IOException;
import java.util.Properties;

public class Configration {

	private static Properties props = new Properties();

	private static final String FILE = "/camunda-client.properties";

	public static String getProtcol() {
		return get("camunda.protcol");
	}
	
	public static String getHost() {
		return get("camunda.host");
	}
	
	public static int getPort() {
		return Integer.parseInt(get("camunda.port"));
	}
	
	public static String getContextPath() {
		return get("camunda.context.path");
	}
	
	private static String get(String key) {
		if (props.isEmpty()) {
			load();
		}
		return props.getProperty(key);
	}

	private static void load() {
		try {
			props.load(Configration.class.getResourceAsStream(FILE));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
