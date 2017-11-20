package org.doula.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.doula.core.LogExecutionTime;
import org.doula.model.Message;
import org.doula.model.MessageAcknowledgement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RxJavaService {
    private static final Logger logger = LoggerFactory.getLogger(RxJavaService.class);

    @LogExecutionTime
    public Single<MessageAcknowledgement> handleMessageSingle(Message message) {
        logger.info("About to Acknowledge");
        return Single.just(message)
                .delay(message.getDelayBy(), TimeUnit.MILLISECONDS)
                .flatMap(msg -> {
                    if (msg.getThrowException()) {
                        return Single.error(new IllegalStateException("Throwing a deliberate exception!"));
                    }
                    return Single.just(new MessageAcknowledgement(message.getId(), message.getPayload(), "From RxJavaService"));
                });
    }

    @LogExecutionTime
    public Observable<MessageAcknowledgement> handleMessageObservable(Message message) {
        logger.info("About to Acknowledge");
        return Observable.just(message)
                .delay(message.getDelayBy(), TimeUnit.MILLISECONDS)
                .flatMap(msg -> {
                    if (msg.getThrowException()) {
                        return Observable.error(new IllegalStateException("Throwing a deliberate exception!"));
                    }
                    return Observable.just(new MessageAcknowledgement(message.getId(), message.getPayload(), "From RxJavaService"));
                });
    }

}
