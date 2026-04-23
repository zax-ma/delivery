package com.study.delivery;

import com.study.delivery.models.Delivery;
import com.study.delivery.services.TipDistributionServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TipDistributionServiceImpl myService) {
		return args -> {
			System.out.println("--- Запуск проверки сервиса ---");

			// Вызываем нужный метод
			var result = myService.distribute(102, new Delivery(111, 123, 8, 5, false));
			System.out.println("Результат: " + result.toString());

			System.out.println("--- Проверка завершена ---");
		};
	}

}
