package nttdata.com.bootcampbc48.clientpersonalaccount.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientpersonalaccount.adapter.Adapter;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.CreateAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.DeleteAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.UpdateAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;
import nttdata.com.bootcampbc48.clientpersonalaccount.repository.PersonalAccountRepository;
import nttdata.com.bootcampbc48.clientpersonalaccount.service.PersonalAccountService;
import nttdata.com.bootcampbc48.clientpersonalaccount.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.clientpersonalaccount.util.mapper.PersonalAccountModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class PersonalAccountServiceImpl implements PersonalAccountService {

    static PersonalAccountModelMapper modelMapper = PersonalAccountModelMapper.singleInstance();
    public final PersonalAccountRepository repository;

    @Override
    public Flowable<Account> findAll() {
        return Adapter.fluxConverter(repository.findAll())
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal clients dont exist.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )));
    }

    @Override
    public Single<Account> findById(String id) {
        return Adapter.monoConverter(repository.findById(id))
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with id number " + id + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(Account.class).toSingle();
    }

    @Override
    public Flowable<Account> findByIdClient(String idClient) {
        return Adapter.fluxConverter(repository.findByIdClient(idClient))
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with document number " + idClient + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(Account.class);

    }

    @Override
    public Single<Account> create(CreateAccountClientDto createAccountClientDto) {

        return Adapter.fluxConverter(repository.findByIdClient(createAccountClientDto.getIdClient()))
                .filter(p -> p.getIdAccountType().equals(1) || p.getIdAccountType().equals(2))
                .map(p -> {
                    throw new BadRequestException(
                            "ClientId",
                            "[save] The client" + createAccountClientDto.getIdClient() + " have other accounts.",
                            "An error occurred while trying to create an item.",
                            getClass(),
                            "save"
                    );
                })
                .switchIfEmpty(repository.save(modelMapper.reverseMapCreateWithDate(createAccountClientDto)))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                ))).toList().map(ee -> ee).cast(Account.class);
    }


    @Override
    public Single<Account> update(UpdateAccountClientDto updateAccountClientDto) {

        return Adapter.monoConverter(repository.findById(updateAccountClientDto.getId())
                .switchIfEmpty(Mono.error(new BadRequestException(
                        "idClient",
                        "An error occurred while trying to update an item.",
                        "The personal client with document number " + updateAccountClientDto.getId() + " does not exists.",
                        getClass(),
                        "update.onErrorResume"
                )))
                .flatMap(p -> Adapter.monoConverter(repository.save(modelMapper.reverseMapUpdate(p, updateAccountClientDto))))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "idClient",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).toSingle();
    }

    @Override
    public Single<Account> delete(DeleteAccountClientDto deleteAccountClientDto) {

        return Adapter.monoConverter(repository.findById(deleteAccountClientDto.getId())
                        .switchIfEmpty(Mono.error(new Exception("An item with the document number " + deleteAccountClientDto.getId() + " was not found. >> switchIfEmpty")))
                        .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deleteAccountClientDto))))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).toSingle();
    }

    private boolean verifyDuplicateAccounts(String accountTypeRegister, String accountTypeExist) {
        boolean result = false;
        switch (accountTypeRegister) {
            case value: {

                yield type;
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + accountTypeRegister);
        }

        return result;
    }
}