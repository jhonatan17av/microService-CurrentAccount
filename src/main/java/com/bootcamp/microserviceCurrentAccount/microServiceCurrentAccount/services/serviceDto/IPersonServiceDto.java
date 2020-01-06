package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.services.serviceDto;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Person;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.dto.AccountDto;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.dto.PersonDtoReturn;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonServiceDto {

	Mono<Person> savePerson(Person person);
	Mono<PersonDtoReturn> findBynumDoc(String numDoc);
	Mono<PersonDtoReturn> updatePerson(Person personDto, String numDoc);
	Flux<AccountDto> lstAccounts(String numDoc);
}
