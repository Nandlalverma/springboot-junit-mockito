package com.jacoc.api.service;

import java.util.List;

import com.jacoc.api.entity.Vendor;

public interface VendorService {

	public Vendor createVendor(Vendor vendor);
	public List<Vendor> getAllVendor();
	public Vendor getVendorbyId(Integer vendorId);
	public List<Vendor> getVendorInformationByVendorName(String vendorName);
	public String deleteVendorById(Integer vendorId);
	public Vendor updateVendorByVendorId(Integer vendorId,Vendor updatedvendor);
	
}
