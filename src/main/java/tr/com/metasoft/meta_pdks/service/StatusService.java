package tr.com.metasoft.meta_pdks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.metasoft.meta_pdks.model.Company;
import tr.com.metasoft.meta_pdks.model.Status;
import tr.com.metasoft.meta_pdks.repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Status save(Status status){



        return statusRepository.save(status);
    }



}
