package nttdata.com.bootcampbc48.clientpersonalaccount.dto;

import lombok.Data;

@Data
public class UpdateAccountTypeClientDto {

    private String id;
    private String idClient;
    private String accountNumber;
    private String idAccountType;
    private short registrationStatus;
}