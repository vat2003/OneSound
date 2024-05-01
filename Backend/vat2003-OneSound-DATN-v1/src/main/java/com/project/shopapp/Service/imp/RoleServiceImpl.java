package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.RoleService;
import com.project.shopapp.entity.Role;
import com.project.shopapp.repository.RoleDAO;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO dao;

    @Override
    public Role createRole(Role Role) {
        // TODO Auto-generated method stub
        return dao.save(Role);
    }

    @Override
    public List<Role> getAllRoles() {
        // TODO Auto-generated method stub
        return dao.findAll();
    }

    @Override
    public Page<Role> getAllRole(Pageable pageable) {
        // TODO Auto-generated method stub
        return dao.findAll(pageable);
    }

    @Override
    public Role getRoleById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Role updateRole(Long id, Role Role) {
        // TODO Auto-generated method stub
        Role Rolee = dao.findById(id).orElse(null);
        Rolee.setName(Role.getName());

        return dao.save(Rolee); // Handle not fou
    }

    @Override
    public void deleteRole(Long id) {
        // TODO Auto-generated method stub
        dao.deleteById(id);
    }

}
