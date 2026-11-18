package com.billing.company.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.company.model.CompanyInfo;

@Repository
public interface CompanyInfoRepository  extends JpaRepository<CompanyInfo, Integer>{

	java.util.Optional<CompanyInfo> findFirstBy();
			
}
