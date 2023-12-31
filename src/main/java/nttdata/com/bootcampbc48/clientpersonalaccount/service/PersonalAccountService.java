package nttdata.com.bootcampbc48.clientpersonalaccount.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.CreateAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.DeleteAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.UpdateAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;

public interface PersonalAccountService {
    public Single<Account> create(CreateAccountClientDto createAccountClientDto);

    public Flowable<Account> findAll();

    public Single<Account> findById(String id);

    public Flowable<Account> findByDocumentNumberAndRegistrationStatus(String idClient, short registrationStatus);

    public Single<Account> update(UpdateAccountClientDto updateAccountClientDto);

    public Single<Account> delete(DeleteAccountClientDto deleteAccountClientDto);


}