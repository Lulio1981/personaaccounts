package nttdata.com.bootcampbc48.clientpersonalaccount.repository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;


@EnableReactiveMongoRepositories
public interface PersonalAccountRepository extends RxJava3CrudRepository<Account, String> {

    Maybe<Account> findById(String id);

    Flowable<Account> findByIdClientAndRegistrationStatus(String idClient, short registrationStatus);

}