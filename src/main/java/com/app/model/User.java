package com.app.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="user_tab")
public class User {

	@Id
	@GeneratedValue
	@Column(name="uid")
	private Integer userId;
	@Column(name="uname")
	private String userName;
	@Column(name="gender")
	private String gender;
	@Column(name="email")
	private String userEmail;
	@Column(name="mobile")
	private String userMobile;
	@Column(name="pwd")
	private String userPassword;
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="user_roles_tab",joinColumns=@JoinColumn(name="uid"))//join column
	@OrderColumn(name="pos")//index column
	@Column(name="roles")//colimn name
	private Set<String> userRoles;
	public User() {
		super();
	}
	
	public User(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", gender=" + gender + ", userEmail=" + userEmail
				+ ", userMobile=" + userMobile + ", userPassword=" + userPassword + ", userRoles=" + userRoles + "]";
	}

	
	
}
