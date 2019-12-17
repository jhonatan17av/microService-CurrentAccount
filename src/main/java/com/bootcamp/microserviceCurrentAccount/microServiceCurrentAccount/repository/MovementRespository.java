package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.repository;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovementRespository extends ReactiveMongoRepository<Movement, String> {

    public Flux<Movement> findBynumAccount(String numAccount);


}
