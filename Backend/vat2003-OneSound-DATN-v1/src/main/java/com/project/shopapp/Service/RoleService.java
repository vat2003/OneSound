package com.project.shopapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.shopapp.entity.Role;

public interface RoleService {
    Role createRole(Role Role);

    List<Role> getAllRoles();

    Page<Role> getAllRole(Pageable pageable);

    Role getRoleById(Long id);

    Role updateRole(Long id, Role Role);

    void deleteRole(Long id);

}
