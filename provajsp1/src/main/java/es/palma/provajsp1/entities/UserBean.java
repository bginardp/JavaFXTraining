package es.palma.provajsp1.entities;

public class UserBean {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	public boolean valid;

	public UserBean() {
		
	}
	public UserBean(String username, String password, String firstName, String lastName) {
	  this.username=username;
	  this.password=password;
	  this.firstName=firstName;
	  this.lastName=lastName;
	  this.valid=true;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		lastName = newLastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		password = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String newUsername) {
		username = newUsername;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		valid = newValid;
	}
}
