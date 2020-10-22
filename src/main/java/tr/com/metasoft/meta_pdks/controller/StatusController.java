package tr.com.metasoft.meta_pdks.controller;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.metasoft.meta_pdks.model.Status;
import tr.com.metasoft.meta_pdks.model.User;
import tr.com.metasoft.meta_pdks.service.StatusService;
import tr.com.metasoft.meta_pdks.service.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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


        LocalTime now = LocalTime.now();
        LocalTime bottomLimit = LocalTime.parse("10:00");
        LocalTime topLimit = LocalTime.parse("17:00");

        if (existUser != null) {

            List<Status> currentDateList = statusService.getByDate(id);     //get existUser's current date list
            List<Status> checkInList = statusService.getByCheckIn(id);     //get existUser's current date check-in list
//            List<Status> orderedTimeList = statusService.getByOrderedTime(id);
//            List<Status> lessOneMinute = statusService.getByTime();

            if (currentDateList.isEmpty()) {
                writeToDb(existUser, "check-in");

            } else {

                for (int i = 0; i < currentDateList.size(); i++) {
                    if (currentDateList.get(i).getStatus().equals("check-in")) {
                        writeToDb(existUser, "check-out");
                        break;
                    }
                    if (currentDateList.get(i).getStatus().equals("check-out")) {
                        writeToDb(existUser, "check-in");
                        break;
                    }
                    if (i == currentDateList.size() - 1){
                        writeToDb(existUser, "check-in");
                    }
                }
            }


//            if (now.isAfter(bottomLimit) && now.isBefore(topLimit)) {
//                writeToDb(existUser, "unknown");
//
//            } else {
//
//                if (!checkInList.isEmpty()) {
//                    writeToDb(existUser, "check-out");
//                } else {
//                    writeToDb(existUser, "check-in");
//                }
//
//            }


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
