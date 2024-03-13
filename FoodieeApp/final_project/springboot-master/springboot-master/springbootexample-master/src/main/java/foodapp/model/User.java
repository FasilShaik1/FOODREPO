package foodapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @javax.validation.constraints.NotBlank(message = "First name is mandatory")
    @javax.validation.constraints.Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String firstName;

    @javax.validation.constraints.NotBlank(message = "Last name is mandatory")
    @javax.validation.constraints.Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String lastName;

    @javax.validation.constraints.NotBlank(message = "Email is mandatory")
    @javax.validation.constraints.Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@javax.validation.constraints.NotBlank(message = "Password is mandatory")
    @javax.validation.constraints.Size(min = 6, max = 16, message = "Password must be between 8 and 16 characters")
    @Column(nullable = false)
    private String password;

    @javax.validation.constraints.NotBlank(message = "Contact number is mandatory")
    @javax.validation.constraints.Pattern(regexp = "[0-9]{10,13}$", message = "Invalid phone number format")
    @Column(nullable = false, unique = true)
    private String contactNumber;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
    }



    @Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }
}
