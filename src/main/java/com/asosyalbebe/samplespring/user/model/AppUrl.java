package com.asosyalbebe.samplespring.user.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AppUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String url;
	private Boolean isRegularExpression;
	private Set<Privilege> privileges = new HashSet<Privilege>();

	public AppUrl() {
	}

	public AppUrl(Long id, String url, Boolean isRegExp, Set<Privilege> privileges) {
		this.id = id;
		this.url = url;
		this.isRegularExpression = isRegExp;
		this.privileges.addAll(privileges);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsRegularExpression() {
		return isRegularExpression;
	}

	public void setIsRegularExpression(Boolean isRegularExpression) {
		this.isRegularExpression = isRegularExpression;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
