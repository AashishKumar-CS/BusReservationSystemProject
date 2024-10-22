package modelBus;

import java.security.Timestamp;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String contactNumber;
	private Timestamp createdAt;
    private Timestamp updatedAt;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	public User(int id, String username, String password, String email, String contactNumber, Timestamp createdAt,
			Timestamp updatedAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public User() {}
	
}
