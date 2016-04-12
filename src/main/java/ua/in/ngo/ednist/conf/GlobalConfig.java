package ua.in.ngo.ednist.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import ua.in.ngo.ednist.upload.FileUploadGroup;
import ua.in.ngo.ednist.upload.FileUploadResolveStrategy;
import ua.in.ngo.ednist.upload.FileUploadService;

@Configuration
@ImportResource("classpath:ua/in/ngo/ednist/conf/security-context.xml")
public class GlobalConfig {

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	@Bean
	public FileUploadService fileUploadService() {
		return new FileUploadService("/srv/static", "ua.in.ngo.ednist", 
						new FileUploadGroup("project", "project", 
								FileUploadResolveStrategy.REPLACE,
									".png"));
	}
}
