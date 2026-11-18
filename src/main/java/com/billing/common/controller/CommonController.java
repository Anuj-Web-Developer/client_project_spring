package com.billing.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.common.service.CommonService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/flights-airport")
public class CommonController {
	
	@Autowired
	CommonService  commonService;
	
	}
