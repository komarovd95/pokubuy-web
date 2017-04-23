package common.example;

import common.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

//@WebServlet(name = "PokubuyApplication", urlPatterns = "/")
public final class PokubuyApplication extends ApplicationController {
    @Override
    protected Front<? super HttpServletRequest, ? extends Response> front() {
        return new HttpServletFt(
            new IndexEp(
                new PageEp(
                    "/WEB-INF/index.jsp"
                )
            )
        );
    }
}
