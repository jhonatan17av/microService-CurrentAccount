package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.dto;

import com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CurrentAccountDtoPerson {

    @NotEmpty
    private String id;
    @NotEmpty(message = "Campo nomBank no puede ser vacio")
    private String nomBank;
    @NotEmpty(message = "Campo numAccount no puede ser vacio")
    private String numAccount;
    @NotEmpty(message = "Campo nomAccount no puede ser vacio")
    private String nomAccount;
    @NotEmpty(message = "Campo typeAccount no puede ser vacio")
    private String typeAccount;
    @NotEmpty(message = "Campo currentBalance no puede ser vacio")
    @Min(value = 0,message = "Valor minimo 0")
    private Double currentBalance;
    @NotEmpty(message = "Campo status no puede ser vacio")
    private String status;
    @NotEmpty(message = "Campo createdAt no puede ser vacio")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    @NotEmpty(message = "Campo updatedAt no puede ser vacio")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;
    @NotEmpty(message = "Campo cantTransactions no puede ser vacio")
    private Integer cantTransactions;
    @NotBlank
    private List<Person> listPersons;

    public CurrentAccountDtoPerson() {
        listPersons = new ArrayList<>();
    }

    public void addListPerson(Person person) {
        this.listPersons.add(person);
    }
}
