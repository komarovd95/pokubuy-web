package ru.ssau.pokubuy.web;

import ru.ssau.pokubuy.web.responses.RsNotAllowed;

@FunctionalInterface
public interface Action {
    Response act(Request request);

    class NoOp implements Action {
        @Override
        public Response act(Request request) {
            return new RsNotAllowed();
        }
    }
}
