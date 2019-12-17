package com.bootcamp.microserviceCurrentAccount.microServiceCurrentAccount.models.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Document(collection = "currentAccounts")
public class CurrentAccount {

    @NotBlank
    @Id
    private String id;
    @NotBlank
    private String numAccount;
    @NotBlank
    private String nomAccount;
    @NotBlank
    private String typeAccount;
    @NotBlank
    private Double currentBalance;
    @NotBlank
    private String status;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updatedAt;

    public CurrentAccount() {
    }

    public CurrentAccount(@NotBlank String numAccount, @NotBlank String nomAccount,
                          @NotBlank String typeAccount, @NotBlank Double currentBalance,
                          @NotBlank String status, @NotBlank Date createdAt, @NotBlank Date updatedAt) {
        this.numAccount = numAccount;
        this.nomAccount = nomAccount;
        this.typeAccount = typeAccount;
        this.currentBalance = currentBalance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
