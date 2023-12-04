package nttdata.com.bootcampbc48.clientpersonalaccount.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.CreateAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.DeleteAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.dto.UpdateAccountClientDto;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;
import nttdata.com.bootcampbc48.clientpersonalaccount.service.impl.PersonalAccountServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client/personal/account")
@Tag(name = "Personal Clients Account Information", description = "Manage personal clients account")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class PersonalAccountController {

    public final PersonalAccountServiceImpl service;

    @GetMapping("/{idClient}/{registrationStatus}")
    public ResponseEntity<Flowable<Account>> findByIdClient(@PathVariable String idClient, @PathVariable short registrationStatus) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findByIdClientAndRegistrationStatus(idClient, registrationStatus));
    }

    @GetMapping("/find/{id}")
    public Single<ResponseEntity<Account>> findById(@PathVariable String id) {

        return service.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping
    public ResponseEntity<Flowable<Account>> findAll() {
        Flowable<Account> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<Account>> create(@RequestBody CreateAccountClientDto createAccountClientDto) {
        return service.create(createAccountClientDto).map(p -> ResponseEntity
                .created(URI.create("/client/personal/".concat(p.get_id())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<Account>> update(@RequestBody UpdateAccountClientDto updateAccountClientDto) {
        return service.update(updateAccountClientDto)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/account"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<Account>> delete(@RequestBody DeleteAccountClientDto deleteAccountClientDto) {
        return service.delete(deleteAccountClientDto)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/account"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}