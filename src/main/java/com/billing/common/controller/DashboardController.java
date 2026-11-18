package com.billing.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.company.model.CompanyInfo;
import com.billing.company.service.CompanyInfoService;
//import com.billing.customer.model.Customer;
//import com.billing.customer.service.CustomerService;
//import com.billing.expanse.model.ExpanseHeader;
//import com.billing.expanse.service.ExpanseService;
//import com.billing.invoice.model.InvoiceHeader;
//import com.billing.invoice.service.InvoiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

		
		 @Autowired
	     CompanyInfoService companyService;
		 
//		 @Autowired
//		 CustomerService customerService;
		 
//		 @Autowired
//		 InvoiceService invoiceService;
		 
//		 @Autowired 
//		 ExpanseService expanseService;
//		
	
    //------------ Company List ---------------------//
//		@GetMapping("/companyForDashboard")
//	    public List<CompanyInfo> getCompanyList(){
//	  	  return companyService.getCompanyList();
//		}

	//-------------- Customer List ----------------//
//		@GetMapping("/customerForDashboard")
//		public List<Customer> getCustomerList(){
//		    return customerService.getCustomerList();
//		}
		
		
	//------------- List Of Invoice --------------//
//		@GetMapping("/invoiceForDashboard")
//		public List<InvoiceHeader> getInvoiveList(){
//			return invoiceService.getInvoiceList();
//		}
//		
		
	//------------- List Of Expense  --------------//
//		@GetMapping("/expanseForDashboard")
//		public List<ExpanseHeader> getExpansesList(){
//			return expanseService.getExpansesList();
//		}
}
