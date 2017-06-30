package org.smendes.room.reservation;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smendes.room.reservation.entity.Reservation;
import org.smendes.room.reservation.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	/** Inicializa a aplicacao Spring boot.
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Inspect the beans provided:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}


	/**
	 * Inicializa a aplicacao criando alguns registros iniciais.
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(ReservationRepository repository) {
		return (args) -> {
			// save a couple of tasks
			repository.save(new Reservation("MATRIZ", "ANFITEATRO", "2017-07-01T13:00", "2017-07-01T17:30",
					"IT Manager", "workshop IOT", 0));
			repository.save(new Reservation("MATRIZ", "SALA PRESIDENCIAL", "2017-07-01T09:00",
					"2017-07-01T12:00", "CIO", "Coffee break com investidores", 5));
			repository.save(new Reservation("FILIAL", "SALA PRINCIPAL", "2017-07-01T08:00", "2017-07-01T12:00",
					"HR Manager", "Integracao novos funcionários", 20));

			// fetch all customers
			log.info("found with findAll():");
			log.info("-------------------------------");
			for (Reservation item : repository.findAll()) {
				log.info(item.toString());
			}

		};
	}

}
