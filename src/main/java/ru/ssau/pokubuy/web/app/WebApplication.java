package ru.ssau.pokubuy.web.app;

import javax.servlet.annotation.WebServlet;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@WebServlet(name = "FrontApplication", urlPatterns = "/*")
public @interface WebApplication {
}
