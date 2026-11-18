package com.billing.urlAccess.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu_url_info")
public class MenuUrlBean {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="menu_id")
	int menuId;
		
	@Column(name="menu_desc")
	private String menuDesc;
	
	@Column(name="menu_url", unique= true)
	private String menuUrl;
	
	@Column(name="target")
	private String target;
	
	@Column(name="parent_info")
	private String parentInfo;
	
	@Column(name="module_url")
	private String moduleUrl;

	@Column(name="menu_icon")
	private String menuIcon;
	
	@Column(name="display_as_menu")
	private int displayAsMenu;
	
	@Column(name="show_confirm", columnDefinition="INT(1) DEFAULT 0")
	private int showConfirm;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getParentInfo() {
		return parentInfo;
	}

	public void setParentInfo(String parentInfo) {
		this.parentInfo = parentInfo;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public int getDisplayAsMenu() {
		return displayAsMenu;
	}

	public void setDisplayAsMenu(int displayAsMenu) {
		this.displayAsMenu = displayAsMenu;
	}

	public int getShowConfirm() {
		return showConfirm;
	}

	public void setShowConfirm(int showConfirm) {
		this.showConfirm = showConfirm;
	}

	
}
