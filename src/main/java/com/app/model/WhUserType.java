package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="wh_USER_type_tab")
public class WhUserType {
	
	@Id
	@GeneratedValue(generator="whuser")
	@GenericGenerator(name="whuser",strategy="increment")
	@Column(name="whuser_id")
	private Integer userId;
	@Column(name="ucode")
	private String userCode;
	@Column(name="utype")
	private String userType;
	@Column(name="uname")
	private String userName;
	@Column(name="ufor")
	private String userFor;
	@Column(name="email")
	private String userEmail;
	@Column(name="mobile")
	private String userNumber;
	@Column(name="idtype")
	private String userIdType;
	@Column(name="otherdocu")
	private String ifOther;
	@Column(name="idnum")
	private String userIdNumber;
	public WhUserType() {
		super();
	}
	public WhUserType(Integer userId) {
		super();
		this.userId = userId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserFor() {
		return userFor;
	}
	public void setUserFor(String userFor) {
		this.userFor = userFor;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserIdType() {
		return userIdType;
	}
	public void setUserIdType(String userIdType) {
		this.userIdType = userIdType;
	}
	public String getIfOther() {
		return ifOther;
	}
	public void setIfOther(String ifOther) {
		this.ifOther = ifOther;
	}
	public String getUserIdNumber() {
		return userIdNumber;
	}
	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber;
	}
	
}
