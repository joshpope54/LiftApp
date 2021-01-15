package dev.joshpope.lift.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Credentials {

    private String email;
    private String password;

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}