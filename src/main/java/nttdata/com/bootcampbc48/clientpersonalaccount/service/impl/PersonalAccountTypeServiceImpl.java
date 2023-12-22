package nttdata.com.bootcampbc48.clientpersonalaccount.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientpersonalaccount.adapter.Adapter;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.CreateAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.DeleteAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.UpdateAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.AccountType;
import nttdata.com.bootcampbc48.clientpersonalaccount.repository.PersonalAccountTypeRepository;
import nttdata.com.bootcampbc48.clientpersonalaccount.service.PersonalAccountTypeService;
import nttdata.com.bootcampbc48.clientpersonalaccount.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.clientpersonalaccount.util.mapper.PersonalAccountTypeModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class PersonalAccountTypeServiceImpl implements PersonalAccountTypeService {

    static PersonalAccountTypeModelMapper modelMapper = PersonalAccountTypeModelMapper.singleInstance();
    public final PersonalAccountTypeRepository repository;

    @Override
    public Flowable<AccountType> findAll() {
        return repository.findAll()
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal clients dont exist.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )));
    }

    @Override
    public Single<AccountType> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with id number " + id + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(AccountType.class).toSingle();
    }

    @Override
    public Single<AccountType> findByAbbreviation(String abbreviation) {
        return repository.findByAbbreviation(abbreviation)

                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with abbreviation " + abbreviation + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(AccountType.class).toSingle();

    }

    @Override
    public Single<AccountType> create(CreateAccountTypeClientDto createAccountTypeClientDto) {

        return repository.findByAbbreviation(createAccountTypeClientDto.getAbbreviation())
                .map(p -> {
                    throw new BadRequestException(
                            "DocumentNumber",
                            "[save] The document number " + createAccountTypeClientDto.getAbbreviation() + " is already in use.",
                            "An error occurred while trying to create an item.",
                            getClass(),
                            "save"
                    );
                })
                .switchIfEmpty(repository.save(modelMapper.reverseMapCreateWithDate(createAccountTypeClientDto)))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                )))
                .cast(AccountType.class);
    }


    @Override
    public Single<AccountType> update(UpdateAccountTypeClientDto updateAccountTypeClientDto) {

        return repository.findById(updateAccountTypeClientDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + updateAccountTypeClientDto.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapUpdate(p, updateAccountTypeClientDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).cast(AccountType.class);
    }

    @Override
    public Single<AccountType> delete(DeleteAccountTypeClientDto deleteAccountTypeClientDto) {

        return repository.findById(deleteAccountTypeClientDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the id number " + deleteAccountTypeClientDto.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deleteAccountTypeClientDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }
}