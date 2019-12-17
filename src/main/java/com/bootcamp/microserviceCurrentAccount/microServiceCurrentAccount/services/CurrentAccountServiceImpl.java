package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.services;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.convertion.ConvertCurrentAccount;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.CurrentAccount;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Movement;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.dto.CurrentAccountDtoPerson;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.repository.CurrentAccountRepository;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.repository.MovementRespository;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.services.serviceDto.IPersonServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class CurrentAccountServiceImpl implements ICurrentAccountService {

    @Autowired
    private CurrentAccountRepository repoCurrentAccount;
    @Autowired
    private MovementRespository repoMovement;
    @Autowired
    private IPersonServiceDto personService;
    @Autowired
    private ConvertCurrentAccount conv;

    @Override
    public Flux<CurrentAccount> findAll() {
        return repoCurrentAccount.findAll() ;
    }

    @Override
    public Mono<CurrentAccount> findById(String id) {
        return repoCurrentAccount.findById(id);
    }

    @Override
    public Mono<CurrentAccount> findByNumAccount(String numAccount) {
        return repoCurrentAccount.findBynumAccount(numAccount);
    }

    @Override
    public Mono<CurrentAccountDtoPerson> saveCurrentAccount(CurrentAccountDtoPerson currentAccountDtoPerson) {
        return repoCurrentAccount.save(conv.toCurrentAccount(currentAccountDtoPerson))
                .flatMap(currentAccount -> {
                    currentAccountDtoPerson.getListPersons().forEach(person -> {
                        person.setIdAccount(currentAccount.getId());
                        person.setNumAccount(currentAccount.getNumAccount());
                        person.setNomAccount(currentAccount.getNomAccount());
                        person.setTypeAccount(currentAccount.getTypeAccount());
                        personService.savePerson(person).block();
                    });
                    return Mono.just(currentAccountDtoPerson);
                });
    }

    @Override
    public Mono<CurrentAccount> updateAccount(CurrentAccount currentAccount) {
        return repoCurrentAccount.save(currentAccount);
    }

    @Override
    public Mono<Void> delete(CurrentAccount currentAccount) {
        return repoCurrentAccount.delete(currentAccount);
    }

    @Override
    public Mono<CurrentAccount> saveMovement(Movement movement) {
        return repoCurrentAccount.findBynumAccount(movement.getNumAccount())
                .flatMap(currentAccount -> {

                    movement.setCreatedAt(new Date());
                    return repoMovement.save(movement)
                            .flatMap(s -> {
                                if (movement.getTypeMovement().trim().toLowerCase().equals("deposito")) {
                                    currentAccount.setUpdatedAt(new Date());
                                    currentAccount.setCurrentBalance(currentAccount.getCurrentBalance() + movement.getBalanceTransaction());
                                    return repoCurrentAccount.save(currentAccount);
                                } else if (movement.getTypeMovement().trim().toLowerCase().equals("retiro")) {
                                    currentAccount.setCurrentBalance(currentAccount.getCurrentBalance() - movement.getBalanceTransaction());
                                    return repoCurrentAccount.save(currentAccount);
                                }
                                return Mono.just(currentAccount);
                            });
                });
    }

    @Override
    public Flux<Movement> findAllMovement() {
        return repoMovement.findAll();
    }

    @Override
    public Flux<Movement> findMovByNumAccount(String numAccount) {
        return repoMovement.findBynumAccount(numAccount);
    }
}
