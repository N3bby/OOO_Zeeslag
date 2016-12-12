package domain.model;

import java.io.FileReader;
import java.util.Properties;

public class GlobalProperties {

	private static GlobalProperties instance = new GlobalProperties();
	
	private Properties properties;
	
	private GlobalProperties() {
		properties = new Properties();
		try {
			properties.load(new FileReader("conf.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static GlobalProperties getInstance() {
		return instance;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
}
