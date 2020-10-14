package tr.com.metasoft.meta_pdks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metasoft.meta_pdks.model.Status;
import tr.com.metasoft.meta_pdks.model.User;

@RestController
@RequestMapping(value = "/status")
public class StatusController {

    @PutMapping(value = "/add")
    public ResponseEntity<User> addStatus(){

    }


}
