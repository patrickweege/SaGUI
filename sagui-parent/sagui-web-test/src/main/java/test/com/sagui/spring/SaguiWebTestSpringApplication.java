package test.com.sagui.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.sagui.common.web.servlet.ResourceServlet;
import com.sagui.ext.web.servlet.ActionServlet;
import com.sagui.ext.web.servlet.OpenServlet;
import com.sagui.ext.web.servlet.PageServlet;
import com.sagui.ext.web.servlet.RenderServlet;

@SpringBootApplication
public class SaguiWebTestSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaguiWebTestSpringApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<ResourceServlet> resourceServletRegistrationBean() {
		ServletRegistrationBean<ResourceServlet> bean = new ServletRegistrationBean<ResourceServlet>(new ResourceServlet(), "/resource/*");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<ActionServlet> actionServletRegistrationBean() {
		ServletRegistrationBean<ActionServlet> bean = new ServletRegistrationBean<ActionServlet>(new ActionServlet(), "/action/*");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<RenderServlet> renderServletRegistrationBean() {
		ServletRegistrationBean<RenderServlet> bean = new ServletRegistrationBean<RenderServlet>(new RenderServlet(), "/Render/*");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<OpenServlet> openServletRegistrationBean() {
		ServletRegistrationBean<OpenServlet> bean = new ServletRegistrationBean<OpenServlet>(new OpenServlet(), "/Open/*");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<PageServlet> pageServletRegistrationBean() {
		ServletRegistrationBean<PageServlet> bean = new ServletRegistrationBean<PageServlet>(new PageServlet(), "/Page/*");
		return bean;
	}
}
