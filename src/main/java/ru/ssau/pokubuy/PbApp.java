package ru.ssau.pokubuy;

import ru.ssau.pokubuy.web.Action;
import ru.ssau.pokubuy.web.Application;
import ru.ssau.pokubuy.web.app.Front;
import ru.ssau.pokubuy.web.endpoints.*;

import javax.servlet.annotation.WebServlet;

//@WebApplication
//@WebServlet(name = "PokubuyApplication", urlPatterns = "/")
public final class PbApp extends Front {
    @Override
    protected Action app() {
        return new Application(
            new EpAuthenticated(
                new EpIndex(
                    new EpPage("/WEB-INF/index.jsp")
                )
            ),
            new EpLastModified(
                new EpStatic(
                    "css"
                )
            )
//            new EpStatic(
//                "css", "png", "jpg", "gif", "woff", "ttf", "js", "otf"
//            )
        );
    }
}
