package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.repository;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.CurrentAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CurrentAccountRepository extends ReactiveMongoRepository<CurrentAccount, String> {

    public Mono<CurrentAccount> findBynumAccount(String numAccount);

}
