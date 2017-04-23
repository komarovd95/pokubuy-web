package fine;

public final class UserAttribute implements Attribute {
    private final User user;

    public UserAttribute(final User user) {
        this.user = user;
    }

    @Override
    public String name() {
        return "signedUser";
    }

    @Override
    public Object value() {
        return this.user;
    }
}
