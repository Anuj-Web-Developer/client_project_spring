package com.billing.attachment.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.billing.project.entity.Project;

@Entity
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String fileType;
	private String fileName;
	private String filePath;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setFilePath(String path) {
		this.filePath = path;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public Attachment(Integer id, String fileType, String fileName, String path) {
		super();
		this.id = id;
		this.fileType = fileType;
		this.fileName = fileName;
		this.filePath = path;
	}

	public Attachment() {
		super();
	}
	
}
