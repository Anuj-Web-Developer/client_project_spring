package com.billing.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.login.model.Role;
import com.billing.login.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   Optional<Role> findByName(RoleName roleName);
}