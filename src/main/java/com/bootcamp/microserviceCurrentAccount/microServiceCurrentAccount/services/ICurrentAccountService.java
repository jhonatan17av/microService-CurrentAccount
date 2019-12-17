package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.services;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.CurrentAccount;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Movement;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.dto.CurrentAccountDtoPerson;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICurrentAccountService {

    Flux<CurrentAccount> findAll();
    Mono<CurrentAccount> findById(String id);
    Mono<CurrentAccount> findByNumAccount(String numAccount);
    Mono<CurrentAccountDtoPerson> saveCurrentAccount(CurrentAccountDtoPerson currentAccountDtoPerson);
    Mono<CurrentAccount> updateAccount(CurrentAccount currentAccount);
    Mono<Void> delete(CurrentAccount currentAccount);

    Mono<CurrentAccount> saveMovement(Movement movement);
    Flux<Movement> findAllMovement();
    Flux<Movement> findMovByNumAccount(String numAccount);

}
