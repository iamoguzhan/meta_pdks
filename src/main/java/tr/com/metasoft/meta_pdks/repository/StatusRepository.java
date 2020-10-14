package tr.com.metasoft.meta_pdks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import tr.com.metasoft.meta_pdks.model.Status;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {



}
