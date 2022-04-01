package kosaShoppingMall;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
// 파일 할떄 얘 없으면
public class WebConfig implements WebMvcConfigurer {

	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      // 앞에 경로가 있고 주소가 있을 수 있음을 명시
	      // spring boot - 세션없이 사용가능한 주소지정
	      // spring - 세션없이는 들어올 수 없는 주소지정
		
	//      registry.addInterceptor(new CertufucatuinInterceptor()) // CertificationInterceptor의 함수값을 가져옴
	//            .addPathPatterns("/**/*")   // 모든 주소
	//            .excludePathPatterns("/static/**/*")
	//            .excludePathPatterns("/register/**/*")
	//            .excludePathPatterns("/help/**/*")
	//            .excludePathPatterns("/login/**/*")
	//            .excludePathPatterns("/corner/**/*");
	      // Interceptor해도 되는 것들(로그인 하지 않아도 들어올 수 있는 것 - 세션 없이 가능한 주소)을 적어줌
	      // 세션이 없으면 / 로 가라고 해놓음?
	   }




	// html 이나 jsp문서그리고 이미지 파일인 경우 view밑에 있는 파일을 불러 올때 404오류가 나는 것을 방지
	 //   아무것도 안적고  / 만 적으면 static밑에 있는 것만 불러와서 오류가 뜨기때문에 이 클레스가 필요한것이다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry reg) {
		reg.addResourceHandler("/**")
		.addResourceLocations("/view/")//업로드한 파일이 view 밑에 들어간것을  열려고 할 때
		.setCachePeriod(14400);
	}
	
		@Bean
		public ResourceBundleMessageSource messageSource() {
			ResourceBundleMessageSource source = new ResourceBundleMessageSource();
			source.setBasename("message/error");
			source.setUseCodeAsDefaultMessage(true);
			source.setDefaultEncoding("utf-8");
			return source;
		}
		
	@Bean(name="jsonView")
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
		
}
