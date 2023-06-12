package jpabook.jpashop;

import jpabook.jpashop.cotroller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		HomeController homeController = new HomeController();

		SpringApplication.run(JpashopApplication.class, args);
	}

}
