package com.deni.gunawan.Sisteminformasiperpustakaan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
public class SistemInformasiPerpustakaanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemInformasiPerpustakaanApplication.class, args);
	}

}
