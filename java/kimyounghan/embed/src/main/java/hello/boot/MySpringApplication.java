package hello.boot;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
	public static void run(Class configClass, String[] args) {
		System.out.println("MySpringApplication.run");

		Tomcat tomcat = new Tomcat();
		Connector connector = new Connector();
		connector.setPort(8080);
		tomcat.setConnector(connector);

		AnnotationConfigWebApplicationContext appcontext = new AnnotationConfigWebApplicationContext();
		appcontext.register(configClass);

		DispatcherServlet dispatcher = new DispatcherServlet(appcontext);

		File baseDir = new File(System.getProperty("java.io.tmpdir"));
		Context context = tomcat.addContext("", baseDir.getAbsolutePath());
		tomcat.addServlet("", "dispatcher", dispatcher);
		context.addServletMappingDecoded("/", "dispatcher");
		try {
			tomcat.start();
		} catch (LifecycleException e) {
			throw new RuntimeException(e);
		}
	}
}
