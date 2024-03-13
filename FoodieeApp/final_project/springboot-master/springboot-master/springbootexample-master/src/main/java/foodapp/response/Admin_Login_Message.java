package foodapp.response;

public class Admin_Login_Message {
	private static final boolean True = false;
	String message;
	Boolean status;
	String role;
	private String token;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Admin_Login_Message(String message, Boolean status, String role,String token) {
		this.message = message;
		this.status = status;
		this.role=role;
		this.token = token;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public static boolean isTrue() {
		return True;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}