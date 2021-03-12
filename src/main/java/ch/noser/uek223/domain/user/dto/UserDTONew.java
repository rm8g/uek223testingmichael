package ch.noser.uek223.domain.user.dto;

public class UserDTONew {

    private String email;
    private String passwordHash;
    private String firstName;
    private String surname;

    public UserDTONew(String email, String passwordHash, String firstName, String surname) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.surname = surname;
    }

    public UserDTONew() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
