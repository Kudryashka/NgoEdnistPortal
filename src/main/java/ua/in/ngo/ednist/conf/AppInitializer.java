package ua.in.ngo.ednist.conf;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class AppInitializer extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createServletApplicationContext() {
//		XmlWebApplicationContext context = new XmlWebApplicationContext();
//		context.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
//		return context;
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(LocalConfig.class);
		return context;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/spring/root-context.xml");
		return context;
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {
				new DelegatingFilterProxy("springSecurityFilterChain"),
				new CharacterEncodingFilter("UTF8", true)
		};
	}
}
