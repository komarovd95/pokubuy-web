package ru.ssau.pokubuy.core.web;

@FunctionalInterface
public interface Response {
    Outcome render();
}
