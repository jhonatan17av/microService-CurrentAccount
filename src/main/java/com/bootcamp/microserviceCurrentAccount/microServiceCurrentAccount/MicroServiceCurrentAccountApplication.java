package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.CurrentAccount;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Movement;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.repository.CurrentAccountRepository;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.repository.MovementRespository;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.services.ICurrentAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@EnableEurekaClient
@SpringBootApplication
public class MicroServiceCurrentAccountApplication implements CommandLineRunner {

	@Autowired
	private CurrentAccountRepository currentAccountService;
	@Autowired
	private MovementRespository movementRespository;
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	private static final Logger log = LoggerFactory.getLogger(MicroServiceCurrentAccountApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceCurrentAccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("currentAccounts").subscribe();
		mongoTemplate.dropCollection("movements").subscribe();

		Flux.just(new CurrentAccount("9898989898","Cuenta Corriente","Standar" ,10000.00,"Active",new Date(),new Date()))
				.flatMap(currentAccount -> currentAccountService.save(currentAccount))
				.subscribe(currentAccount -> log.info("SavingAccount inserted :" + currentAccount.getNumAccount()));

		Flux.just(new Movement("9898989898","deposito",150.0,new Date()))
				.flatMap(movement -> movementRespository.save(movement))
				.subscribe(movement -> log.info("Movement inserted :" + movement.getNumAccount()));
	}
}
