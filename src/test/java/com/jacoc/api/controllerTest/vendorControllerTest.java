package com.jacoc.api.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jacoc.api.controller.VendorController;
import com.jacoc.api.entity.Vendor;
import com.jacoc.api.service.VendorService;
@WebMvcTest(VendorController.class)
public class vendorControllerTest {

	@Autowired
	//@Mock
	private MockMvc mockMvc;
	@MockBean
	//@Mock
	private VendorService vendorService;
	private Vendor vendor;
	@InjectMocks
	private VendorController vendorController;
	
	@BeforeEach
	private void setUp() {
	     MockitoAnnotations.openMocks(this);
	     vendor=new Vendor(11,"rohan","tista","12345566");
	     
	}
	@Test
	@DisplayName("create vendor details test cases")
	public void createVendorDetailsTest() throws Exception {
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String jsonString = ow.writeValueAsString(vendor);
		System.out.println("request Json::::"+jsonString);
		when(vendorService.createVendor(vendor)).thenReturn(vendor);
		mockMvc.perform(post("/api/vendor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
		        .andDo(print())
		        .andExpect(status().isCreated());
	}
   
	@Test
	@DisplayName("get all vendor detials ")
	public void testGetAllVendor() throws Exception {
		when(vendorService.getAllVendor())
		.thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
		mockMvc.perform(get("/api/vendor"))
		.andDo(print())
		.andExpect(status().isOk());
				
				
	}
	@Test
	@DisplayName("get vendor by vendor Id")
	public void testGetVendorbyId() throws Exception {
		when(vendorService.getVendorbyId(1)).thenReturn(vendor);
		mockMvc.perform(get("/api/vendor/vendorId/1"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("get vendor getVendorInforByVendorName by id")
	public void getVendorInforByVendorName() throws Exception {
	   when(vendorService.getVendorInformationByVendorName("peter"))
	   .thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
	   mockMvc.perform(get("/api/vendor/vendorName/peter"))
	   .andDo(print())
	   .andExpect(status().isOk());
	   
	   
	}
	
	@Test
	@DisplayName("delete vendor by vendor id")
	public void deleteVendorById() throws Exception {
		when(vendorService.deleteVendorById(1)).thenReturn("SUCCESS");
		mockMvc.perform(delete("/api/vendor/1"))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	@DisplayName("update vendor by vendor Id")
	public void testUpdateVendor() throws Exception {
		Vendor updatedVendor=new Vendor(1,"updatename","updateState","3434343");
		when(vendorService.updateVendorByVendorId(1, vendor))
		.thenReturn(updatedVendor);
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter wo = mapper.writer().withDefaultPrettyPrinter();
		String jsonString = wo.writeValueAsString(updatedVendor);
		
		mockMvc.perform(put("/api/vendor/1")
				.contentType(MediaType.APPLICATION_JSON)
				 .content(jsonString))
		          .andDo(print())
		          .andExpect(status().isAccepted());
		
				
		
	}
	
	@AfterEach
	public void tearDown() {
		vendor=null;
	}
}
