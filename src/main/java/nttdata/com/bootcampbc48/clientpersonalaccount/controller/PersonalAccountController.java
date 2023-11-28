package nttdata.com.bootcampbc48.clientpersonalaccount.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.personalclient.dto.CreatePersonalClientDto;
import nttdata.com.bootcampbc48.personalclient.dto.DeletePersonalClientDto;
import nttdata.com.bootcampbc48.personalclient.dto.UpdatePersonalClientDto;
import nttdata.com.bootcampbc48.personalclient.entity.PersonalClient;
import nttdata.com.bootcampbc48.personalclient.service.impl.PersonalClientServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client/personal")
@Tag(name = "Personal Clients Information", description = "Manage personal clients")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class PersonalAccountController {

    public final PersonalClientServiceImpl service;

    @GetMapping("/{documentNumber}")
    public Single<ResponseEntity<PersonalClient>> findByDocumentNumber(@PathVariable String documentNumber) {
        return service.findByDocumentNumber(documentNumber).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping("/find/{id}")
    public Single<ResponseEntity<PersonalClient>> findById(@PathVariable String id) {

        return service.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping
    public ResponseEntity<Flowable<PersonalClient>> findAll() {
        Flowable<PersonalClient> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<PersonalClient>> create(@RequestBody CreatePersonalClientDto createPersonalClientDto) {
        return service.create(createPersonalClientDto).map(p -> ResponseEntity
                .created(URI.create("/client/personal/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<PersonalClient>> update(@RequestBody UpdatePersonalClientDto updatePersonalClientDto) {
        return service.update(updatePersonalClientDto)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<PersonalClient>> delete(@RequestBody DeletePersonalClientDto deletePersonalClientDto) {
        return service.delete(deletePersonalClientDto)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}