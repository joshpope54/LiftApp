package dev.joshpope.lift.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String username;
    private String email;
    private String token;
    private String error;

    public LoggedInUser(String username, String email, String token, String error) {
        this.username = username;
        this.email = email;
        this.token = token;
        this.error = error;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }
}