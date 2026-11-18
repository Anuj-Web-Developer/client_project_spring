package com.billing.urlAccess.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.billing.urlAccess.bean.MenuUserMappingBean;

@Repository
public interface MenuUserMappingBeanRepository extends JpaRepository<MenuUserMappingBean, Integer> {
	
	@Query("SELECT c FROM MenuUserMappingBean c where c.userId =:userId and c.menuId =:menuId")
	List<MenuUserMappingBean> getMenuUserMappingList(int userId, int menuId);

	@Query("SELECT c FROM MenuUserMappingBean c where c.userId =:userId")
	List<MenuUserMappingBean> getMenuMappingList(Integer userId);

	 @Modifying
	 @Transactional
	 @Query("delete from MenuUserMappingBean c where c.userId=:userId")
	 void deleteAllPermissionByUserId(int userId);

}
