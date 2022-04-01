package school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer{
	public void addInterceptors(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/view/").setCachePeriod(14400);
		
	}
	
	@Bean
	public ResourceBundleMessageSource messagheSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("message/error"); // message란 폴더에  error.properties란 파일이 있어야 한다.
		source.setUseCodeAsDefaultMessage(true);
		source.setDefaultEncoding("utf-8");
		return source;
	}
}
