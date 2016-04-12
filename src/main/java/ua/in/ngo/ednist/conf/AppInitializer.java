package ua.in.ngo.ednist.conf;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{
				GlobalConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{
				LocalConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {
				new DelegatingFilterProxy("springSecurityFilterChain"),
				new CharacterEncodingFilter("UTF8", true)
		};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		String tmpMultipartDirLocation = "/tmp/ua.in.ngo.ednist/";
		File tmpFile = new File(tmpMultipartDirLocation);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		MultipartConfigElement mce = new MultipartConfigElement(
				tmpMultipartDirLocation, 
				5 * 1024 * 1024, // max file size
				25 * 1024 * 1024, // max request size
				1 * 1024 * 1024); // upload threshold
		registration.setMultipartConfig(mce);
	}
}
