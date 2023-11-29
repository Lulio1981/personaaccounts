package nttdata.com.bootcampbc48.clientpersonalaccount.util.mapper;

import nttdata.com.bootcampbc48.clientpersonalaccount.dto.CreateAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.DeleteAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.UpdateAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.AccountType;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class PersonalAccountTypeModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static PersonalAccountTypeModelMapper instance;

    private PersonalAccountTypeModelMapper() {
    }

    public static PersonalAccountTypeModelMapper singleInstance() {
        if (instance == null) {
            instance = new PersonalAccountTypeModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public AccountType reverseMapCreateWithDate(CreateAccountTypeClientDto createDto) {
        AccountType personalClient = mapper.map(createDto, AccountType.class);

        personalClient.setInsertionDate(new Date());
        personalClient.setRegistrationStatus((short) 1);

        return personalClient;
    }


    public AccountType reverseMapUpdate(AccountType accountType, UpdateAccountTypeClientDto updateDto) {

        accountType.setAbbreviation(updateDto.getAbbreviation());
        accountType.setDescription(updateDto.getDescription());
        accountType.setInterestRate(updateDto.getInterestRate());
        accountType.setMaintenanceCost(updateDto.getMaintenanceCost());
        accountType.setMinimumBalance(updateDto.getMinimumBalance());
        accountType.setTransactionFee(updateDto.getTransactionFee());
        accountType.setTransactionPermissionDate(updateDto.getTransactionPermissionDate());
        accountType.setTransactionsNumber(updateDto.getTransactionsNumber());

        return accountType;
    }

    public AccountType reverseMapDelete(AccountType personalClient, DeleteAccountTypeClientDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}