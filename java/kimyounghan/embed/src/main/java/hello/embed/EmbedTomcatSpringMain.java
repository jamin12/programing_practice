package hello.embed;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import hello.spring.HelloConfig;

public class EmbedTomcatSpringMain {
	public static void main(String[] args) throws LifecycleException {

		Tomcat tomcat = new Tomcat();
		Connector connector = new Connector();
		connector.setPort(8080);
		tomcat.setConnector(connector);

		AnnotationConfigWebApplicationContext appcontext = new AnnotationConfigWebApplicationContext();
		appcontext.register(HelloConfig.class);

		DispatcherServlet dispatcher = new DispatcherServlet(appcontext);

		File baseDir = new File(System.getProperty("java.io.tmpdir"));
		Context context = tomcat.addContext("", baseDir.getAbsolutePath());
		tomcat.addServlet("", "dispatcher", dispatcher);
		context.addServletMappingDecoded("/", "dispatcher");
		tomcat.start();
	}
}
