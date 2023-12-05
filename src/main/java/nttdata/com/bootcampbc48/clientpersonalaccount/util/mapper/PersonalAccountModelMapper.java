package nttdata.com.bootcampbc48.clientpersonalaccount.util.mapper;

import java.util.Date;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.CreateAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.DeleteAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.UpdateAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;
import org.modelmapper.ModelMapper;



public class PersonalAccountModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static PersonalAccountModelMapper instance;

    private PersonalAccountModelMapper() {
    }

    public static PersonalAccountModelMapper singleInstance() {
        if (instance == null) {
            instance = new PersonalAccountModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public Account reverseMapCreateWithDate(CreateAccountClientDto createAccountClientDto) {
        Account personalClient = mapper.map(createAccountClientDto, Account.class);
        personalClient.setInsertionDate(new Date());
        personalClient.setRegistrationStatus((short) 1);
        return personalClient;
    }


    public Account reverseMapUpdate(Account account, UpdateAccountClientDto updateDto) {
        account.setAccountNumber(updateDto.getAccountNumber());
        account.setIdAccountType(updateDto.getIdAccountType());
        return account;
    }

    public Account reverseMapDelete(Account personalClient, DeleteAccountClientDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}