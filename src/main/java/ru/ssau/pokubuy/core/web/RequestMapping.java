package ru.ssau.pokubuy.core.web;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface RequestMapping<T> {
    T map(HttpServletRequest request);
}
