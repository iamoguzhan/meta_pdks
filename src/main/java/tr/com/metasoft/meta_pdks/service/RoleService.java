package tr.com.metasoft.meta_pdks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.metasoft.meta_pdks.model.Company;
import tr.com.metasoft.meta_pdks.model.Role;
import tr.com.metasoft.meta_pdks.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role save(Role role){
        return roleRepository.save(role);
    }

    //get all employees
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    //get an employee by id
    public Role getById(String id){
        return roleRepository.findById(id).orElse(null);
    }

    //delete an employee
    public void delete(Role role){
        roleRepository.delete(role);
    }

    //get a role with role name
    public List<Role> getByRole(String role){
        return roleRepository.findByRole(role);
    }

}
