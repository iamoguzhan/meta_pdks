package tr.com.metasoft.meta_pdks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.metasoft.meta_pdks.model.Status;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

//    @Query(value = "FROM Status s WHERE Date(s.date_time) = CURDATE()")
//    List<Status> findByDate();

    @Query(value = "FROM Status s WHERE CURTIME() - Time(s.date_time) <= 100 AND CURTIME() - Time(s.date_time) > 0 AND Date(s.date_time) = CURDATE()")
    List<Status> findByTime();

    @Query(value = "FROM Status s WHERE Date(s.date_time) = CURDATE() AND user_id = ?1 ORDER BY s.date_time DESC ")
    List<Status> findByDate(String user_id);

    @Query(value = "FROM Status s WHERE Date(s.date_time) = CURDATE() AND user_id = ?1 AND status = 'check-in'")
    List<Status> findByCheckIn(String user_id);

    @Query(value = "FROM Status s WHERE Date(s.date_time) = CURDATE() AND user_id = ?1 AND status = 'check-in' ORDER BY s.date_time DESC")
    List<Status> findByOrderedTime(String user_id);


//    @Query(value = "FROM Status s WHERE Date(s.date_time) = CURDATE() AND Time(s.date_time) > CURTIME()")




}
