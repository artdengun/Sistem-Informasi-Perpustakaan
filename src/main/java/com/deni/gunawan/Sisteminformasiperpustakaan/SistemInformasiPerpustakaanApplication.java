package com.deni.gunawan.Sisteminformasiperpustakaan;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
public class SistemInformasiPerpustakaanApplication {
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemInformasiPerpustakaanApplication.class, args);
	}

}
