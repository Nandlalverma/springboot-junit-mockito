package com.jacoc.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacoc.api.entity.Vendor;
import com.jacoc.api.exception.VendorNotFoundException;
import com.jacoc.api.repository.VendorRepository;

import jakarta.transaction.Transactional;

@Service
public class VendorServiceImpl implements VendorService{

	
    private VendorRepository vendorRepository;	
	public VendorServiceImpl(VendorRepository vendorRepository) {
		this.vendorRepository=vendorRepository;
	}

	@Override
	public Vendor createVendor(Vendor vendor) {
		return vendorRepository.save(vendor);
	}

	@Override
	public List<Vendor> getAllVendor() {
		
		return vendorRepository.findAll();
	}

	//@Override
//	public Vendor getVendorbyId(Integer vendorId) {
//	     Optional<Vendor> vendor = vendorRepository.findVendorById(vendorId);
//	     if(vendor.isPresent()) {
//	    	 return vendor.get();
//	     }
//	     return null;
//	
//	}
	
	@Override
	public Vendor getVendorbyId(Integer vendorId) {
		if(vendorRepository.findVendorByvendorId(vendorId).isEmpty()) {
			throw new VendorNotFoundException("Vendor not present ");
		}
		return vendorRepository.findVendorByvendorId(vendorId).get();
	}
	@Override
	public List<Vendor> getVendorInformationByVendorName(String vendorName) {
		return vendorRepository.findVendorInfoByVendorName(vendorName);
	}
    @Transactional
	@Override
	public String deleteVendorById(Integer vendorId) {
		String deleteVendorById = vendorRepository.deleteVendorByvendorId(vendorId);
		return "Vendor:";
		
	}

	@Override
	public Vendor updateVendorByVendorId(Integer vendorId, Vendor updatedvendor) {
		Optional<Vendor> vendorByvendorId = vendorRepository.findVendorByvendorId(vendorId);
		if(vendorByvendorId.isPresent()) {
			Vendor vendor = vendorByvendorId.get();
			vendor.setVendorName(updatedvendor.getVendorName());
			vendor.setContact(updatedvendor.getContact());
			vendor.setLocation(updatedvendor.getLocation());
		 return	vendorRepository.save(vendor);
		}else {
		   throw new VendorNotFoundException("Vendor not exists:");	
		}
		
	}

}
