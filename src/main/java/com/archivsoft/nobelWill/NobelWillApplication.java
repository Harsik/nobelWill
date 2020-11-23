package com.archivsoft.nobelWill;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;

@SpringBootApplication
public class NobelWillApplication {

	public static void main(String[] args) {
		SpringApplication.run(NobelWillApplication.class, args);
		Security.addProvider(new BouncyCastleProvider());
	}

}