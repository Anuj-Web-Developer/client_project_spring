package com.billing.urlAccess.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing.common.BillingConstants;
import com.billing.common.service.CommonService;
import com.billing.urlAccess.bean.MenuUrlBean;
import com.billing.urlAccess.bean.MenuUserMappingBean;
import com.billing.urlAccess.repository.MenuUrlBeanRepository;
import com.billing.urlAccess.repository.MenuUserMappingBeanRepository;
import com.billing.urlAccess.service.UserAccessService;


@Service
public class UserAccessServiceImpl implements UserAccessService {
	
	@Autowired
	MenuUrlBeanRepository menuUrlBeanRepository;
	
	@Autowired
	MenuUserMappingBeanRepository userMappingRepository;
	
	@Autowired
	CommonService commonService;

	@Override
	public MenuUrlBean getMenuURLBeanByMenuURL(String checkingUrl) {
		List<MenuUrlBean> menuUrlBeanList = menuUrlBeanRepository.getMenuURList(checkingUrl.toUpperCase());
		
		if(menuUrlBeanList != null && menuUrlBeanList.size() > 0){
			return menuUrlBeanList.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public boolean checkMenuAccess(String checkingUrl, Long userId) {
		List<MenuUrlBean> menuUrlBeanList = menuUrlBeanRepository.getMenuURList(checkingUrl.toUpperCase());
		if(menuUrlBeanList != null && menuUrlBeanList.size() > 0){
			MenuUrlBean menuUrlBean = menuUrlBeanList.get(0);
			if(menuUrlBean.getDisplayAsMenu() == BillingConstants.DISPLAY_AS_MENU_SKIP){
				//System.out.println("menuUrlBeanList-"+menuUrlBeanList.size());
				return true;
			}
			int user = userId.intValue();
			List<MenuUserMappingBean> menuUserMappingList = userMappingRepository.getMenuUserMappingList(user  ,menuUrlBean.getMenuId());
		
			if(menuUserMappingList != null && menuUserMappingList.size() > 0){
				//System.out.println("menuUserMappingList-"+menuUserMappingList.size());
				return true;
			}
		}
		return false;
	}

}
