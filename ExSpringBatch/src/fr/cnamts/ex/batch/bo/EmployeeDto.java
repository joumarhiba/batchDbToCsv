package fr.cnamts.ex.batch.bo;

import java.util.Date;

public class EmployeeDto {

	private String name;
	private String phone;
	private String email;
	private String country;
	private String createdAt;
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "EmployeeDto [name=" + name + ", phone=" + phone + ", email=" + email + ", country=" + country
				+ ", createdAt=" + createdAt + "]";
	}
	
	
}
