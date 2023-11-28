package nttdata.com.bootcampbc48.clientpersonalaccount.util.mapper;

import nttdata.com.bootcampbc48.clientpersonalaccount.dto.CreateAccountTypeClientDto;
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

        personalClient.setProfile(updateDto.getProfile());
        personalClient.setFirstName(updateDto.getFirstName());
        personalClient.setLastName(updateDto.getLastName());
        personalClient.setResidenceAddress(updateDto.getResidenceAddress());

        return personalClient;
    }

    public AccountType reverseMapDelete(AccountType personalClient, DeletePersonalClientDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}