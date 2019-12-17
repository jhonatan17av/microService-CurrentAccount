package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.services.serviceDto;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Person;
import reactor.core.publisher.Mono;

public interface IPersonServiceDto {

	public Mono<Person> savePerson(Person person);
	
}
