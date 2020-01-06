package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.controller;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.convertion.ConvertCurrentAccount;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.CurrentAccount;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Movement;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.dto.CurrentAccountDtoPerson;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.services.ICurrentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/currentAccount")
public class CurrentAccountRestController {

  @Autowired
  private ICurrentAccountService currentAccountService;
  @Autowired
  private ConvertCurrentAccount convertCurrentAccount;

  @GetMapping
  public Mono<ResponseEntity<Flux<CurrentAccount>>> findAllAccount() {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(currentAccountService.findAll()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<CurrentAccount>> findByID(@PathVariable String id) {
    return currentAccountService.findById(id)
        .map(savingAccount -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(savingAccount))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/numAccount/{numAccount}")
  public Mono<ResponseEntity<CurrentAccount>> findByNumAccout(@PathVariable String numAccount) {
    return currentAccountService.findByNumAccount(numAccount)
        .map(savingAccount -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(savingAccount))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/nomBank/{nomBank}")
  public Mono<ResponseEntity<Flux<CurrentAccount>>> findByNomBank(@PathVariable String nomBank) {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(currentAccountService.findByNomBank(nomBank)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping("/onPerson/{numDoc}")
  public Mono<ResponseEntity<CurrentAccount>> saveOnPerson(@RequestBody CurrentAccount currentAccount, @PathVariable String numDoc) {
    return Mono.just(currentAccount)
        .flatMap(savingAccountMono1 -> {
          return currentAccountService.saveAccountOnPerson(currentAccount, numDoc)
              .map(s -> ResponseEntity.created(URI.create("/currentAccount"))
                  .contentType(MediaType.APPLICATION_JSON).body(s));
        });
  }

  @PostMapping
  public Mono<ResponseEntity<CurrentAccountDtoPerson>> saveCurrentAccount(@RequestBody CurrentAccountDtoPerson currentAccountDtoPerson) {

    return Mono.just(currentAccountDtoPerson)
        .flatMap(savingAccountDtoMono -> {
          return currentAccountService.saveCurrentAccount(currentAccountDtoPerson)
              .map(s -> ResponseEntity.created(URI.create("/currentAccount"))
                  .contentType(MediaType.APPLICATION_JSON).body(s));
        });
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<CurrentAccount>> updateSavingAccount(@RequestBody CurrentAccount currentAccount, @PathVariable String id) {
    return currentAccountService.findById(id)
        .flatMap(s -> {
          s.setNumAccount(currentAccount.getNumAccount());
          s.setNomAccount(currentAccount.getNomAccount());
          s.setTypeAccount(currentAccount.getTypeAccount());
          s.setCurrentBalance(currentAccount.getCurrentBalance());
          s.setStatus(currentAccount.getStatus());
          s.setCreatedAt(currentAccount.getCreatedAt());
          s.setUpdatedAt(currentAccount.getUpdatedAt());
          return currentAccountService.updateAccount(s);
        }).map(account -> ResponseEntity
            .created(URI.create("/currentAccount".concat(account.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(account))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteSavingAccount(@PathVariable String id) {
    return currentAccountService.findById(id)
        .flatMap(savingAccount -> {
          return currentAccountService.delete(savingAccount)
              .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/saveMov")
  public Mono<ResponseEntity<CurrentAccount>> saveMov(@RequestBody Movement movement) {
    return currentAccountService.saveMovement(movement)
        .map(savingAccount -> ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(savingAccount))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/movements")
  public Mono<ResponseEntity<Flux<Movement>>> findAllMovement() {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(currentAccountService.findAllMovement()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/movements/{numAccount}")
  public Mono<ResponseEntity<Flux<Movement>>> findMovByNumAccount(@PathVariable String numAccount) {
    return Mono.just(ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(currentAccountService.findMovByNumAccount(numAccount)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


}
