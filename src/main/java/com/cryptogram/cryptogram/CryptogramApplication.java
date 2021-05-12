package com.cryptogram.cryptogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Date;


@SpringBootApplication()
@EnableScheduling
public class CryptogramApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptogramApplication.class, args);
	}


	@Scheduled(fixedDelay = 60 * 1000 * 5)
	void someJob() throws IOException {
		System.out.println(new Date());
	}



}
