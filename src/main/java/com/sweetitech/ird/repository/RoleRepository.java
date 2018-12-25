package com.sweetitech.ird.repository;

import com.sweetitech.ird.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(long id);

}