package tr.com.metasoft.meta_pdks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.metasoft.meta_pdks.model.Company;
import tr.com.metasoft.meta_pdks.model.Role;
import tr.com.metasoft.meta_pdks.model.Status;
import tr.com.metasoft.meta_pdks.repository.StatusRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    //save status
    public Status save(Status status){
        return statusRepository.save(status);
    }

    //get a status with time
    public List<Status> getByTime(){
        return statusRepository.findByTime();
    }

    //get a status with date
    public List<Status> getByDate(String user_id){
        return statusRepository.findByDate(user_id);
    }

    //get a status by check-in
    public List<Status> getByCheckIn(String user_id){
        return statusRepository.findByCheckIn(user_id);
    }

    //get a status by ordered time
    public List<Status> getByOrderedTime(String user_id){
        return statusRepository.findByOrderedTime(user_id);
    }



}
