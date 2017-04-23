package ru.ssau.pokubuy.web;

public interface Request {
    Response forward(String path);
    Response serve();
    String header(String name);
    boolean matches(Matching matching);
}
