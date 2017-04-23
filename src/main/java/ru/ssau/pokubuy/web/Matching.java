package ru.ssau.pokubuy.web;

@FunctionalInterface
public interface Matching {
    boolean matches(String method, String path);
}
