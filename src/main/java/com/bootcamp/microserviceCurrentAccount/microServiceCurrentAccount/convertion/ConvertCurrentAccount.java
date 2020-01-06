package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.convertion;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.CurrentAccount;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Movement;
import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.dto.CurrentAccountDtoPerson;
import org.springframework.stereotype.Controller;

@Controller
public class ConvertCurrentAccount {

	public CurrentAccountDtoPerson toCurrentAccountPersonDto(CurrentAccount currentAccount) {
		CurrentAccountDtoPerson dto = new CurrentAccountDtoPerson();
		dto.setNumAccount(currentAccount.getNumAccount());
		dto.setNomAccount(currentAccount.getNomAccount());
		dto.setTypeAccount(currentAccount.getTypeAccount());
		dto.setCurrentBalance(currentAccount.getCurrentBalance());
		dto.setStatus(currentAccount.getStatus());
		dto.setCreatedAt(currentAccount.getCreatedAt());
		dto.setUpdatedAt(currentAccount.getUpdatedAt());
		return dto;
	}
	
	public CurrentAccount toCurrentAccount(CurrentAccountDtoPerson dto) {
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setNomBank(dto.getNomBank());
		currentAccount.setNumAccount(dto.getNumAccount());
		currentAccount.setNomAccount(dto.getNomAccount());
		currentAccount.setTypeAccount(dto.getTypeAccount());
		currentAccount.setCurrentBalance(dto.getCurrentBalance());
		currentAccount.setStatus(dto.getStatus());
		currentAccount.setCreatedAt(dto.getCreatedAt());
		currentAccount.setUpdatedAt(dto.getUpdatedAt());
		currentAccount.setCantTransactions(dto.getCantTransactions());
		return currentAccount;
	}

	public Movement toMovement(CurrentAccountDtoPerson dto) {
		Movement movement = new Movement();
		movement.setNumAccount(dto.getNumAccount());
		movement.setNumAccount(dto.getNumAccount());
		movement.setNumAccount(dto.getNumAccount());
		return movement;
	}

	
}
