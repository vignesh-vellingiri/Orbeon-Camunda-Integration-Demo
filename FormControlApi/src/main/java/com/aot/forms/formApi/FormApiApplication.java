package com.aot.forms.formApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.aot.forms.repository.OrbeonMetaDataRepository;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class FormApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormApiApplication.class, args);
	}

}
