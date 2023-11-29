package nttdata.com.bootcampbc48.clientpersonalaccount.repository;

import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.AccountType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonalAccountTypeRepository extends ReactiveMongoRepository<AccountType, String> {

    public Mono<AccountType> findById(String id);

    public Mono<AccountType> findByAbbreviation(String abbreviation);
}