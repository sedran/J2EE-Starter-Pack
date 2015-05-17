package com.asosyalbebe.samplespring.user.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACL_URL")
public class AclUrl implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "URL", length = 255, unique = true, nullable = false)
    private String url;

    @Column(name = "IS_REG_EXP", nullable = false)
    private Boolean isRegularExpression;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ACL_URL_PRIVILEGE", joinColumns = @JoinColumn(name = "URL_ID"), inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_NAME"))
    private Set<AclPrivilege> privileges = new HashSet<AclPrivilege>();

    public AclUrl() {
    }

    public AclUrl(Long id, String url, Boolean isRegExp, Set<AclPrivilege> privileges) {
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

    public Set<AclPrivilege> getPrivileges() {
	return privileges;
    }

    public void setPrivileges(Set<AclPrivilege> privileges) {
	this.privileges = privileges;
    }

}
