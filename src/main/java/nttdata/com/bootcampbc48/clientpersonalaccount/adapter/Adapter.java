package nttdata.com.bootcampbc48.clientpersonalaccount.adapter;


import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.adapter.rxjava.RxJava3Adapter.*;

public class Adapter {

    public static <T> Maybe<T> monoConverter(Mono<T> source) {
        return monoToMaybe(source);
    }

    public static <T> Single<T> monoSingleConverter(Mono<T> source) {
        return monoToSingle(source);
    }

    public static <T> Flowable<T> fluxConverter(Flux<T> source) {
        return fluxToFlowable(source);
    }

}
