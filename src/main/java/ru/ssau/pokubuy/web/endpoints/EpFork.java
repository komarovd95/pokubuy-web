package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.*;
import ru.ssau.pokubuy.web.matchings.MtGet;
import ru.ssau.pokubuy.web.matchings.PathMatching;
import ru.ssau.pokubuy.web.matchings.MtPost;
import ru.ssau.pokubuy.web.responses.RsNotAllowed;

public class EpFork extends EpMatching {
    private final Endpoint doGet;
    private final Endpoint doPost;

    public EpFork(PathMatching matching, Action doGet) {
        this(matching, doGet, new Action.NoOp());
    }

    public EpFork(PathMatching matching, Action doGet, Action doPost) {
        this(
            matching,
            new EpAction(
                new MtGet(matching),
                doGet
            ),
            new EpAction(
                new MtPost(matching),
                doPost
            )
        );
    }

    public EpFork(PathMatching matching, Endpoint doGet, Endpoint doPost) {
        super(matching);

        this.doGet = doGet;
        this.doPost = doPost;
    }

    @Override
    public Response act(Request request) {
        Response response;
        if (request.matches(this.doGet)) {
            response = this.doGet.act(request);
        } else if (request.matches(this.doPost)) {
            response = this.doPost.act(request);
        } else {
            response = new RsNotAllowed();
        }

        return response;
    }
}
