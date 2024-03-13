package foodapp.dto;

public class Admin_Login_DTO {
    private String firstName;
    private String password;
    
    // Default constructor
    public Admin_Login_DTO() {
    }

    // Parameterized constructor
    public Admin_Login_DTO(String firstName, String password) {
        this.firstName = firstName;
        this.password = password;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
