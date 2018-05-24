package com.market.survey.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



/**
 * 
 * The default AuthenticationManager has a single user (‘user’ username and
 * random password, printed at `INFO` level when the application starts up) for
 * example: password: 27974cb5-ee93-48d1-9e02-d54446480cd6
 * 
 * @author ana
 **/
@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
