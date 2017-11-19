package org.doula.web;


import io.reactivex.Observable;
import io.reactivex.Single;
import org.doula.model.Message;
import org.doula.model.MessageAcknowledgement;
import org.doula.service.RxJavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class RxJavaController {

    private final RxJavaService aService;

    @Autowired
    public RxJavaController(RxJavaService aService) {
        this.aService = aService;
    }

    @RequestMapping(path = "/handleMessageRxJava", method = RequestMethod.POST)
    public Single<MessageAcknowledgement> handleMessage(@RequestBody Message message) {
        return this.aService.handleMessageSingle(message);
    }

    @RequestMapping(path = "/handleMessageRxJavaObs", method = RequestMethod.POST)
    public Observable<MessageAcknowledgement> handleMessageObs(@RequestBody Message message) {
        return this.aService.handleMessageObservable(message);
    }

}
