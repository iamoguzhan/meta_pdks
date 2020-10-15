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
//        status.setStatus("check-in");
//        status.setDate_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return statusRepository.save(status);
    }

    //get a status with date_time
    public List<Status> getByDateTime(){
        return statusRepository.findByDateTime();
    }



}
