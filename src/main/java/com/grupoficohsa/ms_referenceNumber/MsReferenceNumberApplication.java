package com.grupoficohsa.ms_referenceNumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	    "com.grupoficohsa.ms_referenceNumber",
	    "com.grupoficohsa.webclient" 
	})
public class MsReferenceNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReferenceNumberApplication.class, args);
	}

}
