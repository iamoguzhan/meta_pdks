package tr.com.metasoft.meta_pdks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import tr.com.metasoft.meta_pdks.model.Role;
import tr.com.metasoft.meta_pdks.model.Status;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

//    @Query(value = "FROM Status s WHERE s.date_time LIKE '?1%'")
    @Query(value = "FROM Status s WHERE Date(s.date_time) = CURDATE()")
    List<Status> findByDateTime ();



}
