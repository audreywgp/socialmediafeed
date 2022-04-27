package com.dxc.socialmedia.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_data")
public class UserData extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "data_id")
	int dataId;

//	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	int userId;

	@Column(name = "caption")
	String caption;

	@Column(name = "post_type")
	String postType;

	@Column(name = "is_available")
	int isAvailable;

	@Column(name = "location_path")
	String locationPath;

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getLocationPath() {
		return locationPath;
	}

	public void setLocationPath(String locationPath) {
		this.locationPath = locationPath;
	}

	public UserData(int userId, String caption, String postType, String locationPath) {
		super();
		this.userId = userId;
		this.caption = caption;
		this.postType = postType;
		this.locationPath = locationPath;
	}

	public UserData() {

	}

}
