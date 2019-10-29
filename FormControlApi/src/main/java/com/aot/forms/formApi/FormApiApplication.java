package com.aot.forms.formApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.aot.forms.config.JwtAccessTokenCustomizer;
import com.aot.forms.config.OAuth2RestTemplateConfigurer;
import com.aot.forms.config.SecurityConfigurer;
import com.aot.forms.config.SecurityContextUtils;
import com.aot.forms.config.SecurityProperties;
import com.aot.forms.rest.FormsController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
        UserDetailsServiceAutoConfiguration.class})
@EnableJpaRepositories("com.aot.forms.repository")
@Import({OrbeonMetaData.class, SecurityProperties.class,JwtAccessTokenCustomizer.class , OAuth2RestTemplateConfigurer.class ,SecurityConfigurer.class, SecurityContextUtils.class, FormsController.class})
public class FormApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormApiApplication.class, args);
	}

}
