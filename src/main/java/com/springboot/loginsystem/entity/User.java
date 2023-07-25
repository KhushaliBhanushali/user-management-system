package com.springboot.loginsystem.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	
	@Basic
	@Column(name="fname", nullable = false)
	private String firstName;
	
	@Basic
	@Column(name="lname", nullable = false)
	private String lastName;
	
	@Basic
	@Column(name="email", nullable = false)
	private String email;
	
	@Basic
	@Column(name="password", nullable = false)
	private String password;
	
	@Basic
	@Column(name="phone", nullable = false)
	private String phone;
	
	@Basic
	@Column(name="vcode", nullable = false)
	private String vcode;
	
	@Basic
	@Column(name="reg_date", nullable = false)
	private Date regDate;
	
	@Basic
	@Column(name="role", nullable = false)
	private String role;

	public User() {
		super();
	}
	
	public User(int id) {
		super();
		this.id = id;
	}

	public User(String firstName, String lastName, String email, String password, String phone, String vcode,
			Date regDate, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.vcode = vcode;
		this.regDate = regDate;
		this.role = role;
	}

	public User(int id, String firstName, String lastName, String email, String password, String phone, String vcode,
			Date regDate, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.vcode = vcode;
		this.regDate = regDate;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password, phone, regDate, role, vcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone) && Objects.equals(regDate, other.regDate)
				&& Objects.equals(role, other.role) && Objects.equals(vcode, other.vcode);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", vcode=" + vcode + ", regDate=" + regDate
				+ ", role=" + role + "]";
	}
	
	
}
