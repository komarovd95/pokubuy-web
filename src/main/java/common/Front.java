package common;

public interface Front<RQ, RS> {
    Request request(RQ rq);
    RS response(Request request);
}
