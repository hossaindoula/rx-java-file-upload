package org.doula.service;

import org.doula.core.LogExecutionTime;
import org.doula.model.Message;
import org.doula.model.MessageAcknowledgement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;

@Service
public class ReactorService {
    private static final Logger logger = LoggerFactory.getLogger(ReactorService.class);


    @LogExecutionTime
    public Mono<MessageAcknowledgement> handleMessageMono(Message message) {
        return Mono
                .delay(Duration.ofMillis(message.getDelayBy()))
                .then(Mono.just(message))
                .map(msg -> Tuples.of(msg, msg.getThrowException()))
                .flatMap(tup -> {
                    if (tup.getT2()) {
                        return Mono.error(new RuntimeException("Throwing a deliberate Exception!"));
                    }
                    Message msg = tup.getT1();
                    return Mono.just(new MessageAcknowledgement(msg.getId(), msg.getPayload(), "Response from ReactorService"));
                });
    }

    @LogExecutionTime
    public Flux<MessageAcknowledgement> handleMessageFlux(Message message) {
        return Flux.just(message)
                .delaySubscription(Duration.ofMillis(message.getDelayBy()))
                .map(msg -> Tuples.of(msg, msg.getThrowException()))
                .flatMap(tup -> {
                    if (tup.getT2()) {
                        return Flux.error(new RuntimeException("Throwing a deliberate Exception!"));
                    }
                    Message msg = tup.getT1();
                    return Flux.just(new MessageAcknowledgement(msg.getId(), msg.getPayload(), "Response from ReactorService"));
                });
    }

}
