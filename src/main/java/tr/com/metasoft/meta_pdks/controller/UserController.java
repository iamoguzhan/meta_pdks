package tr.com.metasoft.meta_pdks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tr.com.metasoft.meta_pdks.model.Role;
import tr.com.metasoft.meta_pdks.model.User;
import tr.com.metasoft.meta_pdks.service.CompanyService;
import tr.com.metasoft.meta_pdks.service.RoleService;
import tr.com.metasoft.meta_pdks.service.StatusService;
import tr.com.metasoft.meta_pdks.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    CompanyService companyService;

    @Autowired
    StatusService statusService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //add new users
    @PostMapping(value = "/add")
    public User addUser(@Validated @RequestBody User user) {

        if (user.getPassword() != null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        return userService.save(user);
    }

    //get all users
    @GetMapping(value = "/getAll")
    public List<User> getUsers() {
        return userService.findAll();
    }

    //get user by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") String id) {
        User user = userService.getById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(user);
        }
    }

    //update an employee
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") String id, @RequestBody User userDetail) {
        User user = userService.getById(id);
//sdasdasd
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (userDetail.getName() != null) {
            user.setName(userDetail.getName());
        }
        if (userDetail.getSurname() != null) {
            user.setSurname(userDetail.getSurname());
        }
        if (userDetail.getPhone() != null) {
            user.setPhone(userDetail.getPhone());
        }
        if (userDetail.getUsername() != null) {
            user.setUsername(userDetail.getUsername());
        }
        if (userDetail.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(userDetail.getPassword()));
        }
        if (userDetail.getRoles() != null) {
            user.setRoles(userDetail.getRoles());
        }
        if (userDetail.getStatuses() != null) {
            user.setStatuses(userDetail.getStatuses());
        }
        if (userDetail.getCompanies() != null) {
            user.setCompanies(companyService.getByCompany(userDetail.getCompanies().get(0).getCompany()));
        }
        if (userDetail.getRoles() != null){
            user.setRoles(roleService.getByRole(userDetail.getRoles().get(0).getRole()));
        }


        User updateUser = userService.save(user);

        return ResponseEntity.ok().body(updateUser);

    }

    //delete a user
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") String id) {
        User user = userService.getById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userService.delete(user);

        return ResponseEntity.ok().build();
    }

}
