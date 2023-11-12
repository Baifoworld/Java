package es.iespuertodelacruz.jmrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class IncidenciasSpring2Application {

	public static void main(String[] args) {
		SpringApplication.run(IncidenciasSpring2Application.class, args);
	}

	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity webSecurity) throws Exception
	{
	webSecurity
	.ignoring()
	.antMatchers("/**");
	}
	}
}
