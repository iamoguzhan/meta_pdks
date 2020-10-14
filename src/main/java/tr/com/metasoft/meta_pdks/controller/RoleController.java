package tr.com.metasoft.meta_pdks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tr.com.metasoft.meta_pdks.model.Role;
import tr.com.metasoft.meta_pdks.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    //add new roles
    @PostMapping(value = "/add")
    public Role addRole(@Validated @RequestBody Role role) {
        return roleService.save(role);
    }

    //get all roles
    @GetMapping(value = "/getAll")
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    //get role by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Role> getById(@PathVariable(value = "id") String id) {

        Role role = roleService.getById(id);

        if (role == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(role);
        }
    }

    //update an role
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") String id, @RequestBody Role roleDetail) {

        Role role = roleService.getById(id);

        if (role == null) {
            return ResponseEntity.notFound().build();
        }

        if (roleDetail.getRole() != null) {
            role.setRole(roleDetail.getRole());
        }

        Role updateRole = roleService.save(role);

        return ResponseEntity.ok().body(updateRole);

    }

    //delete a role
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable(value = "id") String id) {

        Role role = roleService.getById(id);

        if (role == null) {
            return ResponseEntity.notFound().build();
        }

        roleService.delete(role);

        return ResponseEntity.ok().build();
    }

}
