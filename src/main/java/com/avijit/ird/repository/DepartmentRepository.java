package com.avijit.ird.repository;

import com.avijit.ird.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 3/5/19 at 2:15 PM
 * @project ird
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);

    List<Department> findAllByIsDeleted(Boolean status);
}
