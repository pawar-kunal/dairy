package com.diary.Controllers;

import com.diary.Masters.BirthDayMaster;
import com.diary.Payloads.Requests.BirthDayRequest;
import com.diary.Payloads.Responses.MainResponse;
import com.diary.Services.BirthDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/birthday")
public class BirthDayController {
    @Autowired
    private BirthDayService birthDayService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody BirthDayRequest birthDayRequest){
        MainResponse mainResponse = this.birthDayService.add(birthDayRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody BirthDayRequest birthDayRequest){
        MainResponse mainResponse = this.birthDayService.update(birthDayRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/birtday-details/{birthDayId}")
    public ResponseEntity birthdayDetails(@PathVariable("birthDayId") Integer birthDayId){
        BirthDayMaster birthDayMaster = this.birthDayService.birthdayDetails(birthDayId);
        return new ResponseEntity(birthDayMaster, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{birthDayId}")
    public ResponseEntity deleteBirthday(@PathVariable("birthDayId") Integer birthDayId){
        MainResponse mainResponse = this.birthDayService.delete(birthDayId);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all-birthdays")
    public ResponseEntity allBirthDays(){
        List<BirthDayMaster> birthDayMasters = this.birthDayService.allBirthDays();
        return new ResponseEntity(birthDayMasters, HttpStatus.OK);
    }
}
