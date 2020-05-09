package com.h3b.investment.model;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "tb_bcc_index")
public class BccIndexEntity {

	@Id
	@Column(name = "code", nullable=false)
	private int code;
	
	@Column(name = "name", nullable=false, length = 50)
	private String name;
	
	@Column(name = "variation", nullable=false)
	private int variation;
	
	@Column(name = "description", nullable=false, length = 500)
	private String description;
	
	@Column(name = "creationTime", nullable=false)
	private Calendar creationTime;
	
	@Column(name = "userCreation", nullable=false, length = 50)
	private String userCreation;
	
	@Column(name = "updatedTime")
	private Calendar updatedTime;
	
	@Column(name = "userUpdated", length = 50)
	private String userUpdated;

	public BccIndexEntity() {
		
	}
	
	public BccIndexEntity(int code, String name, int variation, String description, Calendar creationTime,
			String userCreation, Calendar updatedTime, String userUpdated) {
		this.code = code;
		this.name = name;
		this.variation = variation;
		this.description = description;
		this.creationTime = creationTime;
		this.userCreation = userCreation;
		this.updatedTime = updatedTime;
		this.userUpdated = userUpdated;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVariation() {
		return variation;
	}

	public void setVariation(int variation) {
		this.variation = variation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}

	public String getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

	public Calendar getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Calendar updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(String userUpdated) {
		this.userUpdated = userUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((updatedTime == null) ? 0 : updatedTime.hashCode());
		result = prime * result + ((userCreation == null) ? 0 : userCreation.hashCode());
		result = prime * result + ((userUpdated == null) ? 0 : userUpdated.hashCode());
		result = prime * result + variation;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BccIndexEntity other = (BccIndexEntity) obj;
		if (code != other.code)
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (updatedTime == null) {
			if (other.updatedTime != null)
				return false;
		} else if (!updatedTime.equals(other.updatedTime))
			return false;
		if (userCreation == null) {
			if (other.userCreation != null)
				return false;
		} else if (!userCreation.equals(other.userCreation))
			return false;
		if (userUpdated == null) {
			if (other.userUpdated != null)
				return false;
		} else if (!userUpdated.equals(other.userUpdated))
			return false;
		if (variation != other.variation)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BccIndexEntity [code=" + code + ", name=" + name + ", variation=" + variation + ", description="
				+ description + ", creationTime=" + creationTime + ", userCreation=" + userCreation + ", updatedTime="
				+ updatedTime + ", userUpdated=" + userUpdated + "]";
	}

	
	
	
}
