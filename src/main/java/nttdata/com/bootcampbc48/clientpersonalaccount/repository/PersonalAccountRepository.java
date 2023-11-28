package nttdata.com.bootcampbc48.clientpersonalaccount.repository;

import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonalAccountRepository extends ReactiveMongoRepository<Account, String> {

    public Mono<Account> findById(String id);

    public Flux<Account> findByIdClient(String idClient);

}