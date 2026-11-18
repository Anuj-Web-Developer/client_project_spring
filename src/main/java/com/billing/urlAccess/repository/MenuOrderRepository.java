package com.billing.urlAccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.urlAccess.bean.MenuOrder;

@Repository
public interface MenuOrderRepository extends JpaRepository<MenuOrder, Integer>{

}
