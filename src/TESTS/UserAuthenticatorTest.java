package TESTS;

import API.UserAuthenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthenticatorTest {


    @Test
    void testAuthenticateUser_Success() {
        // Test για σωστή αυθεντικοποίηση
        String role = UserAuthenticator.authenticateUser("Nikos", "12345".toCharArray());
        assertEquals("Administrator", role);

        role = UserAuthenticator.authenticateUser("Maria", "12345".toCharArray());
        assertEquals("Customer", role);
    }

    @Test
    void testAuthenticateUser_Failure() {
        // Test για αποτυχία αυθεντικοποίησης (λάθος username ή password)
        String role = UserAuthenticator.authenticateUser("NonExistentUser", "password".toCharArray());
        assertNull(role);

        role = UserAuthenticator.authenticateUser("Nikos", "wrongPassword".toCharArray());
        assertNull(role);
    }

    @Test
    void testAuthenticateUser_EmptyCredentials() {
        // Test για κενά credentials
        String role = UserAuthenticator.authenticateUser("", "".toCharArray());
        assertNull(role);
    }
}
