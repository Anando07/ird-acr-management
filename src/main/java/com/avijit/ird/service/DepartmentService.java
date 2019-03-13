package com.avijit.ird.service;

import com.avijit.ird.domain.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 3/5/19 at 2:16 PM
 * @project ird
 */
@Service
public interface DepartmentService {

    void delete(Long deptId);

    void update(Department department);

    Department save(String name);

    List<Department> getDepartments();

    Department readDepartment(Long deptId);
}
