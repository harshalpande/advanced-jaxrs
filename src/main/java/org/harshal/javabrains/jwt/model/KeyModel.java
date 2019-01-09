package org.harshal.javabrains.jwt.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class KeyModel{

	
	@Id
	@Column(name = "USER_NAME")
	private String user;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "TTL")
	private Timestamp ttl;

	@Column(name = "KEY")
	private String key;
	
	@Lob
	@Column(name = "TOKEN")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Timestamp getTtl() {
		return ttl;
	}

	public void setTtl(Timestamp ttl) {
		this.ttl = ttl;
	}

	@Override
	public String toString() {
		return "KeyModel [user=" + user + ", password=" + password + ", ttl=" + ttl + ", key=" + key + ", token="
				+ token + "]";
	}
}
