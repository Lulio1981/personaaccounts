package nttdata.com.bootcampbc48.clientpersonalaccount.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.CreateAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.DeleteAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.UpdateAccountTypeClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.AccountType;
import nttdata.com.bootcampbc48.clientpersonalaccount.service.impl.PersonalAccountTypeServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client/personal/accounttype")
@Tag(name = "Personal Clients Information", description = "Manage personal clients")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class PersonalAccountTypeController {

    public final PersonalAccountTypeServiceImpl service;

    @GetMapping("/{abbreviation}")
    public Single<ResponseEntity<AccountType>> findByDocumentNumber(@PathVariable String abbreviation) {
        return service.findByAbbreviation(abbreviation).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping("/find/{id}")
    public Single<ResponseEntity<AccountType>> findById(@PathVariable String id) {

        return service.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping
    public ResponseEntity<Flowable<AccountType>> findAll() {
        Flowable<AccountType> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<AccountType>> create(@RequestBody CreateAccountTypeClientDto createAccountTypeClientDto) {
        return service.create(createAccountTypeClientDto).map(p -> ResponseEntity
                .created(URI.create("/client/personal/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<AccountType>> update(@RequestBody UpdateAccountTypeClientDto updateAccountTypeClientDto) {
        return service.update(updateAccountTypeClientDto)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<AccountType>> delete(@RequestBody DeleteAccountTypeClientDto deleteAccountTypeClientDto) {
        return service.delete(deleteAccountTypeClientDto)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}