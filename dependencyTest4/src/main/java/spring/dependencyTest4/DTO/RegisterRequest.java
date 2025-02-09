package spring.dependencyTest4.DTO;

public class RegisterRequest {
	private String email;
	private String name;
	private String password;
	private String confirmPassword;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void SetConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public boolean isPasswordEqualConfirmPassword() {
		return password.equals(confirmPassword);
	}
}
