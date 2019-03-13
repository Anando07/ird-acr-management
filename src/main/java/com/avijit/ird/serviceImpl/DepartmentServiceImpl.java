package com.avijit.ird.serviceImpl;

import com.avijit.ird.common.LogStatus;
import com.avijit.ird.configuration.MySessionInfo;
import com.avijit.ird.domain.AuditLog;
import com.avijit.ird.domain.Department;
import com.avijit.ird.repository.AuditLogRepository;
import com.avijit.ird.repository.DepartmentRepository;
import com.avijit.ird.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Date;
import java.util.List;

/**
 * @author Avijit Barua
 * @created_on 3/5/19 at 2:18 PM
 * @project ird
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    @Autowired
    AuditLogRepository logRepository;
    @Autowired
    MySessionInfo sessionInfo;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(String name) {
        if (departmentRepository.findByName(name) != null) {
            throw new EntityExistsException("This department already exists!");
        } else {
            Department department = departmentRepository.save(new Department(name, false));
            logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.createDept + new Date()));
            return department;
        }
    }

    @Override
    public void delete(Long deptId) {
        Department department = departmentRepository.getOne(deptId);
        department.setDeleted(true);
        departmentRepository.save(department);
        logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.deleteDept + new Date()));

    }

    @Override
    public void update(Department dept) {
        Department department = departmentRepository.getOne(dept.getId());
        if (!department.getName().equalsIgnoreCase(dept.getName()) && departmentRepository.findByName(dept.getName()) != null)
            throw new EntityExistsException("This department already exists!");
        department.setName(dept.getName());
        departmentRepository.save(department);
        logRepository.save(new AuditLog(sessionInfo.getCurrentUser().getName() + LogStatus.updateDept + new Date()));
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAllByIsDeleted(false);
    }

    @Override
    public Department readDepartment(Long deptId) {
        return departmentRepository.getOne(deptId);
    }
}
