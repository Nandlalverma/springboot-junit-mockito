package com.jacoc.api.repository;

import java.util.Optional;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jacoc.api.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer>{

	public Optional<Vendor> findVendorByvendorId(Integer vendorId);
	public String deleteVendorByvendorId(Integer vendorId);
	public List<Vendor> findVendorInfoByVendorName(String vendorName);
}
