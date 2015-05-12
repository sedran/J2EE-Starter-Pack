package com.asosyalbebe.samplespring.user.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Privilege implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}
