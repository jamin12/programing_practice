package hello.container;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;

public class AppInitV2Spring implements AppInit {
	@Override
	public void onStartUp(ServletContext servletContext) {
		System.out.println("AppInitV2Spring.onStartUp");

		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(HelloConfig.class);

		DispatcherServlet dispatcher = new DispatcherServlet(appContext);
		servletContext.addServlet("dispatcherV2", dispatcher)
			.addMapping("/spring/*");
	}
}
