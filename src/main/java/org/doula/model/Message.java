package org.doula.model;

/**
 * Created by tasnim on 11/18/2017.
 */
public class Message {

    private String id;
    private String payload;
    private Boolean throwException;
    private Long delayBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Boolean getThrowException() {
        return throwException;
    }

    public void setThrowException(Boolean throwException) {
        this.throwException = throwException;
    }

    public Long getDelayBy() {
        return delayBy;
    }

    public void setDelayBy(Long delayBy) {
        this.delayBy = delayBy;
    }
}
