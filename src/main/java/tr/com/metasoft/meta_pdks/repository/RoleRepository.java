package tr.com.metasoft.meta_pdks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.metasoft.meta_pdks.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "FROM Role r WHERE r.role = ?1")
    List<Role> findByRole(String role);

}
