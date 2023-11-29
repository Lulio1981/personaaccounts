package nttdata.com.bootcampbc48.clientpersonalaccount.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.*;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.AccountType;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.AccountType;
import reactor.core.publisher.Mono;

public interface PersonalAccountTypeService {
    public Single<AccountType> create(CreateAccountTypeClientDto createAccountTypeClientDto);

    public Flowable<AccountType> findAll();

    public Single<AccountType> findById(String id);

    public Single<AccountType> findByAbbreviation(String abbreviation);

    public Single<AccountType> update(UpdateAccountTypeClientDto updateAccountTypeClientDto);

    public Single<AccountType> delete(DeleteAccountTypeClientDto deleteAccountTypeClientDto);


}