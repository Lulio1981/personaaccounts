package nttdata.com.bootcampbc48.clientpersonalaccount.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientpersonalaccount.service.PersonalAccountTypeService;
import nttdata.com.bootcampbc48.personalclient.adapter.Adapter;
import nttdata.com.bootcampbc48.personalclient.dto.CreatePersonalClientDto;
import nttdata.com.bootcampbc48.personalclient.dto.DeletePersonalClientDto;
import nttdata.com.bootcampbc48.personalclient.dto.UpdatePersonalClientDto;
import nttdata.com.bootcampbc48.personalclient.entity.PersonalClient;
import nttdata.com.bootcampbc48.personalclient.repository.PersonalClientRepository;
import nttdata.com.bootcampbc48.personalclient.service.PersonalClientService;
import nttdata.com.bootcampbc48.personalclient.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.personalclient.util.mapper.PersonalClientModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class PersonalAccountTypeServiceImpl implements PersonalAccountTypeService {

    static PersonalClientModelMapper modelMapper = PersonalClientModelMapper.singleInstance();
    public final PersonalClientRepository repository;

    @Override
    public Flowable<PersonalClient> findAll() {
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
    public Single<PersonalClient> findById(String id) {
        return Adapter.monoConverter(repository.findById(id))
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with id number " + id + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(PersonalClient.class).toSingle();
    }

    @Override
    public Single<PersonalClient> findByDocumentNumber(String documentNumber) {
        return Adapter.monoConverter(repository.findByDocumentNumber(documentNumber))

                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with document number " + documentNumber + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(PersonalClient.class).toSingle();

    }

    @Override
    public Single<PersonalClient> create(CreatePersonalClientDto createPersonalClientDto) {

        return Adapter.monoConverter(repository.findByDocumentNumber(createPersonalClientDto.getDocumentNumber()))
                .map(p -> {
                    throw new BadRequestException(
                            "DocumentNumber",
                            "[save] The document number " + createPersonalClientDto.getDocumentNumber() + " is already in use.",
                            "An error occurred while trying to create an item.",
                            getClass(),
                            "save"
                    );
                })
                .switchIfEmpty(Maybe.defer(() ->
                        Adapter.monoConverter(repository.save(modelMapper.reverseMapCreateWithDate(createPersonalClientDto)))
                ))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                )))
                .cast(PersonalClient.class).toSingle();
    }


    @Override
    public Single<PersonalClient> update(UpdatePersonalClientDto updatePersonalClientDto) {

        return Adapter.monoConverter(repository.findByDocumentNumber(updatePersonalClientDto.getDocumentNumber()))
                .switchIfEmpty(Maybe.error(new Exception("An item with the document number " + updatePersonalClientDto.getDocumentNumber() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> Adapter.monoConverter(repository.save(modelMapper.reverseMapUpdate(p, updatePersonalClientDto))))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).toSingle();
    }

    @Override
    public Single<PersonalClient> delete(DeletePersonalClientDto deletePersonalClientDto) {

        return Adapter.monoConverter(repository.findByDocumentNumber(deletePersonalClientDto.getDocumentNumber())
                        .switchIfEmpty(Mono.error(new Exception("An item with the document number " + deletePersonalClientDto.getDocumentNumber() + " was not found. >> switchIfEmpty")))
                        .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deletePersonalClientDto))))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).toSingle();
    }
}