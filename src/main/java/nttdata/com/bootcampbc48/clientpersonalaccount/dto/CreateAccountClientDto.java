package nttdata.com.bootcampbc48.clientpersonalaccount.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAccountClientDto {

    private String documentNumber;
    private String accountNumber;
    private String idAccountType;
    private short registrationStatus;
    private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;

}