package com.sweetitech.ird.Repository;

import com.sweetitech.ird.Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(long id);

}