package ru.ssau.pokubuy.core.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface Outcome {
    void apply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
