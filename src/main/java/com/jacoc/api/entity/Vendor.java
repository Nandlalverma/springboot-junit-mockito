package com.jacoc.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_vendor")
public class Vendor {
	
	@Id
    @Column(name = "vendor_id", nullable = false)
	private Integer vendorId;
	@Column(name="vendor_name" , nullable = false)
	private String vendorName;
	@Column(name = "locaiton",nullable = false)
	private String location;
	@Column(name = "contact", nullable = false)
	private String contact;
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", location=" + location + ", contact="
				+ contact + "]";
	}
	public Vendor(Integer vendorId, String vendorName, String location, String contact) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.location = location;
		this.contact = contact;
	}
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
