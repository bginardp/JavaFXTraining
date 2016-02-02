package es.palmademallorca.bg.common.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Set;

/**
 * classe que segueix el patró Singleton
 *
 * @author BERNAT
 *
 */
public class PropertiesCache {
	private final Properties configProp = new Properties();

	private PropertiesCache() {
		// Private constructor to restrict new instances
		InputStream is = null;
		try {
			File f = new File("factu.properties");
			is = new FileInputStream(f);

		} catch (Exception e) {
			is = null;
		}
		try {
			if (is == null) {
				is = getClass().getClassLoader().getResourceAsStream("factu.properties");
			}
			configProp.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Bill Pugh Solution for singleton pattern
	private static class LazyHolder {
		private static final PropertiesCache INSTANCE = new PropertiesCache();
	}

	public static PropertiesCache getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

	public Set<String> getAllPropertyNames() {
		return configProp.stringPropertyNames();
	}

	public boolean containsKey(String key) {
		return configProp.containsKey(key);
	}
	public void setProperty(String key, String value){
		  configProp.setProperty(key, value);
		}
	public static void saveParamChanges() {
	    try {
	        Properties props = new Properties();
	        props.setProperty("app.NomApl", "factu");
	        props.setProperty("app.Version", "1.0");
	        File f = new File("factu.properties");
	        OutputStream out = new FileOutputStream( f );
	        props.store(out, "This is an optional header comment string");
	    }
	    catch (Exception e ) {
	        e.printStackTrace();
	    }
	}


}
