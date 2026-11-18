/*
 * package com.tms.login.model;
 * 
 * import java.io.Serializable; import java.util.Date;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.Table; import
 * javax.persistence.Temporal; import javax.persistence.TemporalType;
 * 
 * import com.fasterxml.jackson.annotation.JsonIgnore;
 * 
 * @Entity
 * 
 * @Table(name="admin") public class Admin implements Serializable {
 * 
 * 
 *//**
	* 
	*//*
		 * 
		 * private static final long serialVersionUID = 1L;
		 * 
		 * @Id
		 * 
		 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
		 * 
		 * @Column(name="email_id") private String emailId;
		 * 
		 * @Column(name="staff_groups_id") private int staffGroupsId;
		 * 
		 * @Column(name="password") private String password;
		 * 
		 * @Column(name="first_name") private String firstName;
		 * 
		 * @Column(name="last_name") private String lastName;
		 * 
		 * @Column(name="contact_no") private String contactNo;
		 * 
		 * @Column(name="address") private String address;
		 * 
		 * @Column(name="city") private String city;
		 * 
		 * @Column(name="state") private String state;
		 * 
		 * @Column(name="country") private String country;
		 * 
		 * @Column(name="profile_pic") private String profilePic;
		 * 
		 * @Column(name="about_me") private String aboutMe;
		 * 
		 * @Column(name="device)_detail") private String deviceDetail;
		 * 
		 * @Column(name="status") private int status;
		 * 
		 * @Column(name="role") private int role;
		 * 
		 * @JsonIgnore
		 * 
		 * @Column(name="last_login_time")
		 * 
		 * @Temporal(TemporalType.TIMESTAMP) private Date lastLoginTime = new Date();
		 * 
		 * @Column(name="social_links") private String socialLinks;
		 * 
		 * @JsonIgnore
		 * 
		 * @Column(name="created_time")
		 * 
		 * @Temporal(TemporalType.TIMESTAMP) private Date createdTime = new Date();
		 * 
		 * @Column(name="last_login_ip") private String lastLoginIp;
		 * 
		 * @Column(name="post") private String post;
		 * 
		 * public long getId() { return id; }
		 * 
		 * public void setId(long id) { this.id = id; }
		 * 
		 * public String getEmailId() { return emailId; }
		 * 
		 * public void setEmailId(String emailId) { this.emailId = emailId; }
		 * 
		 * public int getStaffGroupsId() { return staffGroupsId; }
		 * 
		 * public void setStaffGroupsId(int staffGroupsId) { this.staffGroupsId =
		 * staffGroupsId; }
		 * 
		 * public String getPassword() { return password; }
		 * 
		 * public void setPassword(String password) { this.password = password; }
		 * 
		 * public String getFirstName() { return firstName; }
		 * 
		 * public void setFirstName(String firstName) { this.firstName = firstName; }
		 * 
		 * public String getLastName() { return lastName; }
		 * 
		 * public void setLastName(String lastName) { this.lastName = lastName; }
		 * 
		 * public String getContactNo() { return contactNo; }
		 * 
		 * public void setContactNo(String contactNo) { this.contactNo = contactNo; }
		 * 
		 * public String getAddress() { return address; }
		 * 
		 * public void setAddress(String address) { this.address = address; }
		 * 
		 * public String getCity() { return city; }
		 * 
		 * public void setCity(String city) { this.city = city; }
		 * 
		 * public String getState() { return state; }
		 * 
		 * public void setState(String state) { this.state = state; }
		 * 
		 * public String getCountry() { return country; }
		 * 
		 * public void setCountry(String country) { this.country = country; }
		 * 
		 * public String getProfilePic() { return profilePic; }
		 * 
		 * public void setProfilePic(String profilePic) { this.profilePic = profilePic;
		 * }
		 * 
		 * public String getAboutMe() { return aboutMe; }
		 * 
		 * public void setAboutMe(String aboutMe) { this.aboutMe = aboutMe; }
		 * 
		 * public String getDeviceDetail() { return deviceDetail; }
		 * 
		 * public void setDeviceDetail(String deviceDetail) { this.deviceDetail =
		 * deviceDetail; }
		 * 
		 * public int getStatus() { return status; }
		 * 
		 * public void setStatus(int status) { this.status = status; }
		 * 
		 * public int getRole() { return role; }
		 * 
		 * public void setRole(int role) { this.role = role; }
		 * 
		 * public Date getLastLoginTime() { return lastLoginTime; }
		 * 
		 * public void setLastLoginTime(Date lastLoginTime) { this.lastLoginTime =
		 * lastLoginTime; }
		 * 
		 * public String getSocialLinks() { return socialLinks; }
		 * 
		 * public void setSocialLinks(String socialLinks) { this.socialLinks =
		 * socialLinks; }
		 * 
		 * public Date getCreatedTime() { return createdTime; }
		 * 
		 * public void setCreatedTime(Date createdTime) { this.createdTime =
		 * createdTime; }
		 * 
		 * public String getLastLoginIp() { return lastLoginIp; }
		 * 
		 * public void setLastLoginIp(String lastLoginIp) { this.lastLoginIp =
		 * lastLoginIp; }
		 * 
		 * public String getPost() { return post; }
		 * 
		 * public void setPost(String post) { this.post = post; }
		 * 
		 * public static long getSerialversionuid() { return serialVersionUID; }
		 * 
		 * 
		 * 
		 * }
		 */