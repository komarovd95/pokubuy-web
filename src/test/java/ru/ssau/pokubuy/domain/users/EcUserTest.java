package ru.ssau.pokubuy.domain.users;

import org.junit.Test;

import java.security.MessageDigest;

import static org.junit.Assert.*;

public class EcUserTest {
    @Test
    public void testEncryptedPassword() throws Exception {
        User fakeUser = new FakeUser("secret");

        MessageDigest instance = MessageDigest.getInstance("SHA-512");
        instance.update(fakeUser.password().getBytes());

        User ecUser = new EcUser(fakeUser);

        assertEquals(new String(instance.digest()), ecUser.password());
    }

    @Test(expected = RuntimeException.class)
    public void testNoSuchAlgorithm() throws Exception {
        User fakeUser = new FakeUser("secret");

        MessageDigest instance = MessageDigest.getInstance("SHA-512");
        instance.update(fakeUser.password().getBytes());

        User ecUser = new EcUser(fakeUser, "NO SUCH ALGO");

        assertEquals(new String(instance.digest()), ecUser.password());
    }

}