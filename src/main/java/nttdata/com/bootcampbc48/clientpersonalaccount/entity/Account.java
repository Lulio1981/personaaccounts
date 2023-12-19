package nttdata.com.bootcampbc48.clientpersonalaccount.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class to manage attributes accounts persons.
 *
 * @author Lulio Herrera Mestanza
 *
 * @version 0.1 december_2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Account {

  @Id
  private String _id;
  private String documentNumber;
  private String accountNumber;
  private String idAccountType;
  private short registrationStatus;
  private Date insertionDate;
  private String insertionUser;
  private String insertionTerminal;

}
