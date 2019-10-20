package com.app.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc // it is Spring WEB MVC AppConfig
@EnableTransactionManagement // enable commit/rollback
@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = "com.app") // <context:component-scan
@Import(SecurityConfig.class)
public class AppConfig implements WebMvcConfigurer {
	@Autowired
	private Environment env;

	// 1. DataSource
	@Bean
	public BasicDataSource dsObj() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("dc"));
		ds.setUrl(env.getProperty("url"));
		ds.setUsername(env.getProperty("un"));
		ds.setPassword(env.getProperty("pwd"));
		ds.setInitialSize(1);
		ds.setMaxIdle(1);
		ds.setMinIdle(1);
		ds.setMaxTotal(5);
		return ds;
	}

	// 2. SessionFactory
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dsObj());
		sf.setHibernateProperties(props());
		/* TODO pass model classes */
		sf.setPackagesToScan("com.app.model");
		return sf;
	}

	private Properties props() {
		Properties p = new Properties();
		p.put("hibernate.dialect", env.getProperty("dialect"));
		p.put("hibernate.show_sql", env.getProperty("showsql"));
		p.put("hibernate.format_sql", env.getProperty("fmtsql"));
		p.put("hibernate.hbm2ddl.auto", env.getProperty("ddlauto"));
		return p;
	}

	// 3. HibernateTemplate
	@Bean
	public HibernateTemplate hibernateTemplate() {
		HibernateTemplate ht = new HibernateTemplate();
		ht.setSessionFactory(sessionFactoryBean().getObject());
		return ht;
	}

	// 4. Transaction Manager
	@Bean
	public HibernateTransactionManager htmObj() {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactoryBean().getObject());
		return htm;
	}

	// 5. View Resolver
	@Bean
	public InternalResourceViewResolver ivr() {
		InternalResourceViewResolver v = new InternalResourceViewResolver();
		v.setPrefix(env.getProperty("mvc.prefix")); // location of UI file
		v.setSuffix(env.getProperty("mvc.suffix")); // extension of UI file
		return v;
	}

	// 6. Multipart Resolver
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cm = new CommonsMultipartResolver();
		return cm;
	}

	// 7. Email Configuration
	@Bean
	public JavaMailSenderImpl mail() {
		JavaMailSenderImpl mail = new JavaMailSenderImpl();
		mail.setHost(env.getProperty("email.host"));
		mail.setPort(env.getProperty("email.port", Integer.class));
		mail.setUsername(env.getProperty("email.usr"));// enter your emailId.
		mail.setPassword(env.getProperty("email.pwd"));// enter ur password.
		mail.setJavaMailProperties(eprops());
		return mail;
	}

	private Properties eprops() {
		Properties p = new Properties();
		p.put("mail.smtp.auth", env.getProperty("email.auth"));
		p.put("mail.smtp.starttls.enable", env.getProperty("email.ssl.enbl"));
		return p;
	}

	// 8. Password Encoder
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry reg) {
		reg.addResourceHandler("/resources/**").addResourceLocations("/resources/");

	}
}
