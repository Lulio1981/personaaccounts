package nttdata.com.bootcampbc48.clientpersonalaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AccountType {

    @Id
    private String id;
    private String description;
    private String abbreviation;
    private String isoCurrencyCode;
    private BigDecimal interestRate;
    private BigDecimal transactionFee;
    private Long transactionsNumber;
    private BigDecimal maintenanceCost;
    private BigDecimal minimumBalance;
    private Date transactionPermissionDate;
    private short registrationStatus;
    private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;

}
