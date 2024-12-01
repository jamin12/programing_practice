package hello.external;

import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaSystemProperties {
	public static void main(String[] args) {
		Properties getenv = System.getProperties();
		for (Object s : getenv.keySet()) {
			log.info("env {}: {}", s, getenv.getProperty(String.valueOf(s)));
		}

		String url = System.getProperty("url");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
		log.info("url={}", url);
		log.info("username={}", username);
		log.info("password={}", password);

	}
}
