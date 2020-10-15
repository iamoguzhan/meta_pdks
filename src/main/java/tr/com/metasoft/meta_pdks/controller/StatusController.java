package tr.com.metasoft.meta_pdks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.metasoft.meta_pdks.model.Status;
import tr.com.metasoft.meta_pdks.model.User;
import tr.com.metasoft.meta_pdks.service.StatusService;
import tr.com.metasoft.meta_pdks.service.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/status")
public class StatusController {

    @Autowired
    UserService userService;

    @Autowired
    StatusService statusService;

    @PutMapping(value = "/add/{id}")
    public void addStatus(@PathVariable(value = "id") String id) {

        User existUser = userService.getById(id);

        List<Status> statusList = statusService.getByDateTime();


        if (!statusList.isEmpty() && existUser.getStatuses().containsAll(statusList)) {
            writeToDb(existUser, "check-out");
        } else {
            writeToDb(existUser, "check-in");
        }

    }

    public void writeToDb(User existUser, String status) {
        Status tempStatus = new Status();
        tempStatus.setDate_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        tempStatus.setStatus(status);

        existUser.getStatuses().add(tempStatus);

        userService.save(existUser);
    }


}
