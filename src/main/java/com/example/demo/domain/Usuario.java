package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario", schema = "public")
public class Usuario implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "email", unique = true, nullable = false)
	@NotNull
	@Email
	@Size(min = 3, max = 255)
	private String email;

	@Column(name = "address", nullable = false)
	@NotNull
	@Size(min = 3, max = 255)
	@NotEmpty
	private String address;

	@Column(name = "enable", nullable = false)
	@NotNull
	@Size(min = 1, max = 1)
	@NotEmpty
	private String enable;

	@Column(name = "name", nullable = false)
	@NotNull
	@Size(min = 4, max = 255)
	@NotEmpty
	private String name;

	@Column(name = "phone", nullable = false)
	@NotNull
	@Size(min = 6, max = 255)
	@NotEmpty
	private String phone;

	@Column(name = "token", nullable = false)
	@NotNull
	@Size(max = 255)
	@NotEmpty
	private String token;

	public Usuario() {
	}

	public Usuario(String email, String address, String enable, String name, String phone, String token) {
		this.email = email;
		this.address = address;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
