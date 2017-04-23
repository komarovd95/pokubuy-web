package ru.ssau.pokubuy.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface Response {
    void send(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
