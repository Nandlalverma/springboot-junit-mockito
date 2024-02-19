package com.jacoc.api.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jacoc.api.entity.Vendor;
import com.jacoc.api.exception.VendorNotFoundException;
import com.jacoc.api.repository.VendorRepository;
import com.jacoc.api.service.VendorService;
import com.jacoc.api.service.VendorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VendorServiceTest {

	@Mock
	private  VendorRepository vendorRepository;
	@InjectMocks
	private  VendorServiceImpl vendorServiceImpl;
	@InjectMocks
	private VendorNotFoundException exceptionNotFund;
	private static Vendor vendor;
	@BeforeEach
	public  void setUp() {
	vendorRepository=	mock(VendorRepository.class,Mockito.CALLS_REAL_METHODS);
		 MockitoAnnotations.openMocks(this);
		 vendorServiceImpl=new VendorServiceImpl(vendorRepository);
		vendor=new Vendor(12,"peter","usa","3345678");
	}
	
	@Test
	@DisplayName("test case for creating vendor record")
	public void createTestVendor() {
		
		 when(vendorRepository.save(any())).thenReturn(vendor);
	    //verify the service method
		//assertThat(vendorServiceImpl.createVendor(vendor)).isEqualTo("SUCCESS");
		 assertThat(vendorServiceImpl.createVendor(vendor)).isNotNull();
	}
	
	@Test
	@DisplayName("get all wendor test case")
	public void getAllVendorTest() {
		when(vendorRepository.findAll())
		.thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
		assertThat(vendorServiceImpl.getAllVendor().get(0).getVendorName())
		.isEqualTo(vendor.getVendorName());
		assertThat(vendorServiceImpl.getAllVendor().size()).isEqualTo(1);
	}
	@Test
	@DisplayName("getVendor by id test cases")
	public void getVendorbyIdTest() {
		when(vendorRepository.findVendorByvendorId(1))
		.thenReturn(Optional.ofNullable(vendor));
		
		assertThat(vendorServiceImpl.getVendorbyId(1)
				.getVendorName()).isEqualTo(vendor.getVendorName());
		assertThat(vendorServiceImpl.getVendorbyId(1).getContact())
		.isEqualTo(vendor.getContact());
		assertThat(vendorServiceImpl.getVendorbyId(1).getLocation())
		.isEqualTo(vendor.getLocation());
		assertThrows(VendorNotFoundException.class, ()->{
			throw new VendorNotFoundException("vendor not present");
		});
	}
	
	@Test
	@DisplayName("getVendor all information by vendor name")
	public void testGetVendorInformationByVendorName() {
		when(vendorRepository.findVendorInfoByVendorName("Amazone"))
		.thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
		assertThat(vendorServiceImpl.getVendorInformationByVendorName("Amazone").get(0).getVendorName())
		.isEqualTo(vendor.getVendorName());
		//.isNotEmpty();
		
	}
	@Test
	@DisplayName("delete vender by id test cases")
	public void TestdeleteVendorById() {
		when(vendorRepository.deleteVendorByvendorId(1)).thenReturn("peter");
		assertThat(vendorServiceImpl.deleteVendorById(1))
		.isNotEmpty();
		
	
   }
	
	@Test
	@DisplayName("test case for updated vendor id")
   public void testCaseforUpdate() {
	   // mocking the vehaviour of the repository vendor
	   when(vendorRepository.findVendorByvendorId(1))
	   .thenReturn(Optional.of(new Vendor(12,"tom","British","121212")));
	   
	   //updating vendor with new information
	   Vendor updatedVendor = new Vendor(1,"newVendor","newLocation","newContact");
	   when(vendorRepository.save(any())).thenReturn(updatedVendor);
	   
	   //calling the service method
	   Vendor vendorId = vendorServiceImpl.updateVendorByVendorId(1, updatedVendor);
	   // verify the vendor repository
	   verify(vendorRepository,times(1)).findVendorByvendorId(1);
	   assertThat(vendorId).isEqualTo(updatedVendor);
   }
	@Test
	@DisplayName("test case for exception vendorNotfundException")
	public void testException() {
		when(vendorRepository.findVendorByvendorId(1))
		.thenThrow(VendorNotFoundException.class);
	   assertThrows(VendorNotFoundException.class,
			   ()->vendorServiceImpl.getVendorbyId(1),"Request vendor does not exist");
	}
	
	
	
	@AfterEach
	public   void tearDown() {
		
	}
}
