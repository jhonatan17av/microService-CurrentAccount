package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.repository;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface MovementRespository extends ReactiveMongoRepository<Movement, String> {

    Flux<Movement> findBynumAccount(String numAccount);
    Flux<Movement> findByNumAccountAndCreatedAtBetween(String numAccount, Date date1, Date date2);


}
