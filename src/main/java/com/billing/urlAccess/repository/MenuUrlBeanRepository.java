package com.billing.urlAccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.billing.common.BillingConstants;
import com.billing.urlAccess.bean.MenuUrlBean;

@Repository
public interface MenuUrlBeanRepository extends JpaRepository<MenuUrlBean, Integer>{

	
	@Query("SELECT c FROM MenuUrlBean c where upper(c.menuUrl) =:checkingUrl")
	List<MenuUrlBean> getMenuURList(String checkingUrl);

	@Query("SELECT c FROM MenuUrlBean c where c.displayAsMenu !="+BillingConstants.DISPLAY_AS_MENU_SKIP)
	List<MenuUrlBean> getMenuUrlList();

}
