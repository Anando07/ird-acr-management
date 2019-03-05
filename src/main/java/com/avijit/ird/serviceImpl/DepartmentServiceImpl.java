package com.avijit.ird.serviceImpl;

import com.avijit.ird.domain.Department;
import com.avijit.ird.repository.DepartmentRepository;
import com.avijit.ird.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(String name) {
        if(departmentRepository.findByName(name) !=null){
            throw new EntityExistsException("This department already exists!");}
        else {
            return departmentRepository.save(new Department(name, false));
        }
    }



    @Override
    public Department delete(Long deptId) {
        Department department = departmentRepository.getOne(deptId);
        department.setDeleted(true);
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department dept) {
        Department department =departmentRepository.getOne(dept.getId());
        if(!department.getName().equalsIgnoreCase(dept.getName()) && departmentRepository.findByName(dept.getName())!=null)
            throw new EntityExistsException("This department already exists!");
        department.setName(dept.getName());
        return departmentRepository.save(department);
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
