package nttdata.com.bootcampbc48.clientpersonalaccount.repository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import nttdata.com.bootcampbc48.clientpersonalaccount.entity.Account;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonalAccountRepository extends RxJava3CrudRepository<Account, String> {

    public Maybe<Account> findById(String id);

    public Flowable<Account> findByIdClientAndRegistrationStatus(String idClient, short registrationStatus);

}