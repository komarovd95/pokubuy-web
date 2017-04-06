package ru.ssau.pokubuy.actions;

import ru.ssau.pokubuy.core.web.Get;
import ru.ssau.pokubuy.core.web.Post;
import ru.ssau.pokubuy.core.web.Request;
import ru.ssau.pokubuy.core.web.Response;
import ru.ssau.pokubuy.core.web.responses.JspPage;

public final class SignInAction implements Get, Post {
    @Override
    public String path() {
        return "/signin";
    }

    @Override
    public String page() {
        return "/WEB-INF/signin.jsp";
    }

    @Override
    public Response get(Request request) {
        return new JspPage(this.page());
    }

    @Override
    public Response post(Request request) {
        return null;
    }
}
