package com.billing.urlAccess.service;

import com.billing.urlAccess.bean.MenuUrlBean;

public interface UserAccessService {

	MenuUrlBean getMenuURLBeanByMenuURL(String checkingUrl);

	boolean checkMenuAccess(String checkingUrl, Long userId);

}
