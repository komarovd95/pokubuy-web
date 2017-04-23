package fine;

public interface Principal {
    String identity();
    boolean hasRole(Role role);

    String ANONYMOUS = "ANONYMOUS";
}
