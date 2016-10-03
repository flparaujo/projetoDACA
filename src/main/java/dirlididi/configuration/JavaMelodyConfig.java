package dirlididi.configuration;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.MonitoringSpringAdvisor;
import net.bull.javamelody.Parameter;
import net.bull.javamelody.SessionListener;
import net.bull.javamelody.SpringDataSourceBeanPostProcessor;

@Configuration
@SuppressWarnings("javadoc")
public class JavaMelodyConfig implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new SessionListener());
	}

	@Bean
	public FilterRegistrationBean javaMelody() {
		final FilterRegistrationBean javaMelody = new FilterRegistrationBean();
		javaMelody.setFilter(new MonitoringFilter());
		javaMelody.setAsyncSupported(true);
		javaMelody.setName("javamelody");
		javaMelody.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
		javaMelody.addInitParameter(Parameter.LOG.getCode(), Boolean.toString(true));

		// to add basic auth:
		// javaMelody.addInitParameter(Parameter.AUTHORIZED_USERS.getCode(),
		// "admin:pwd");
		// to change the default storage directory:
		// javaMelody.addInitParameter(Parameter.STORAGE_DIRECTORY.getCode(),
		// "/tmp/javamelody");

		javaMelody.addUrlPatterns("/*");
		return javaMelody;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	/*
	 * Monitorando o JDBC
	 */
	@Bean
	public SpringDataSourceBeanPostProcessor monitoringDataSourceBeanPostProcessor() {
		SpringDataSourceBeanPostProcessor processor = new SpringDataSourceBeanPostProcessor();
		processor.setExcludedDatasources(null);
		return processor;
	}
	/*
	 * Monitorando todos os Services
	 */
	@Bean
	public MonitoringSpringAdvisor springServiceMonitoringAdvisor() {
		final MonitoringSpringAdvisor interceptor = new MonitoringSpringAdvisor();
		interceptor.setPointcut(new AnnotationMatchingPointcut(Service.class));
		return interceptor;
	}
	/*
	 * Monitorando todos os RestControllers
	 */
	@Bean
	public MonitoringSpringAdvisor springRestControllerMonitoringAdvisor() {
		final MonitoringSpringAdvisor interceptor = new MonitoringSpringAdvisor();
		interceptor.setPointcut(new AnnotationMatchingPointcut(RestController.class));
		return interceptor;
	}
}
