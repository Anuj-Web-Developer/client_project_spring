package com.billing.company.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.billing.attachment.entity.Attachment;

@Entity
public class CompanyInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String userName;
	private String mobile;
	private String email;
	private String designation;
	private String companyName;
	private String companyAddress;
	private String billingAddress;
	private String gstno;
	private String companyPhone;
	private String website;
	
	private String logoName;
	private String singnatureName;
	
//	@OneToOne(orphanRemoval = true)
//	@NotNull(message = "company logo is required")
//	private Attachment logo;
	
//	@OneToOne(orphanRemoval = true)
//	@NotNull(message = "Signature is required")
//	private Attachment signature;

	public Integer getId() {
		return id;
	}

//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getGstno() {
		return gstno;
	}

	public void setGstno(String gstno) {
		this.gstno = gstno;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

//	public Attachment getSignature() {
//		return signature;
//	}
//
//	public void setSignature(Attachment signature) {
//		this.signature = signature;
//	}

//	public Attachment getLogo() {
//		return logo;
//	}
//
//	public void setLogo(Attachment logo) {
//		this.logo = logo;
//	}
	
	

	public CompanyInfo() {
		super();		
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getSingnatureName() {
		return singnatureName;
	}

	public void setSingnatureName(String singnatureName) {
		this.singnatureName = singnatureName;
	}
	
}
