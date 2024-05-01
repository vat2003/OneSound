package com.project.shopapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.RoleService;
import com.project.shopapp.entity.Role;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class RoleController {
    @Autowired
    private RoleService RoleService;

    public RoleController(RoleService RoleService) {
        this.RoleService = RoleService;
    }

    @GetMapping("/Role")
    public List<Role> getAllSingers() {
        return RoleService.getAllRoles();
    }

    @GetMapping("/Role/Role")
    public Page<Role> getAllSingers(Pageable pageable) {
        return RoleService.getAllRole(pageable);
    }

    @PostMapping("/Role")
    public Role createEmployee(@RequestBody Role Role) {
        System.out.println(Role);
        return RoleService.createRole(Role);
    }

    @GetMapping("/Role/{id}")
    public ResponseEntity<Role> getSingerById(@PathVariable Long id) {
        Role employee = RoleService.getRoleById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/Role/{id}")
    public Role updateEmployee(@PathVariable long id, @RequestBody Role Role) {
        return RoleService.updateRole(id, Role);
    }

    @DeleteMapping("/Role/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        Role employeeToDelete = RoleService.getRoleById(id);
        RoleService.deleteRole(employeeToDelete.getId());
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
