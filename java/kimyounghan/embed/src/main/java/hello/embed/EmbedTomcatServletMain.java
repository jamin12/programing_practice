package hello.embed;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import hello.servlet.HelloServlet;

public class EmbedTomcatServletMain {
	public static void main(String[] args) throws LifecycleException {
		System.out.println("EmbedTomcatServletMain.main");

		Tomcat tomcat = new Tomcat();
		Connector connector = new Connector();
		connector.setPort(8080);
		tomcat.setConnector(connector);

		File baseDir = new File(System.getProperty("java.io.tmpdir"));
		Context context = tomcat.addContext("", baseDir.getAbsolutePath());

		tomcat.addServlet("", "helloServlet", new HelloServlet());
		context.addServletMappingDecoded("/hello-servlet", "helloServlet");
		tomcat.start();
	}
}
