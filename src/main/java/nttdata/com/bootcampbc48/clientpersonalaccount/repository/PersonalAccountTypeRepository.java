package nttdata.com.bootcampbc48.clientpersonalaccount.repository;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.AccountType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonalAccountTypeRepository extends RxJava3CrudRepository<AccountType, String> {

    public Maybe<AccountType> findById(String id);

    public Maybe<AccountType> findByAbbreviation(String abbreviation);
}