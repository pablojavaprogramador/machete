package com.tecnoplacita.machete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tecnoplacita.machete.exceptions","com.tecnoplacita.machete.security","com.tecnoplacita.machete.controller","com.tecnoplacita.machete.repository","com.tecnoplacita.machete.config","com.tecnoplacita.machete.model","com.tecnoplacita.machete.service", "com.tecnoplacita.machete.config","com.tecnoplacita.machete.utils"})
@EntityScan("com.tecnoplacita.machete.model")
@ConfigurationPropertiesScan("com.tecnoplacita.machete.config")
@EnableJpaRepositories("com.tecnoplacita.machete.repository")

public class MacheteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacheteApplication.class, args);
	}

}
