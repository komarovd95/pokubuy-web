package ru.ssau.pokubuy.web.attributes;

import ru.ssau.pokubuy.web.Attribute;

public final class AbSignedUser implements Attribute<String> {
    private final String signedUser;

    public AbSignedUser(String signedUser) {
        this.signedUser = signedUser;
    }

    @Override
    public String name() {
        return "signedUser";
    }

    @Override
    public String value() {
        return this.signedUser;
    }
}
