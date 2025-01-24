package com.diary.Services.Impl;

import com.diary.Masters.BirthDayMaster;
import com.diary.Payloads.Requests.BirthDayRequest;
import com.diary.Payloads.Responses.MainResponse;
import com.diary.Repositories.BirthDayRepository;
import com.diary.Services.BirthDayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BirthDayServiceImpl implements BirthDayService {
    @Autowired
    private BirthDayRepository birthDayRepository;

    @Override
    public MainResponse add(BirthDayRequest birthDayRequest) {
        MainResponse mainResponse = new MainResponse();
        BirthDayMaster birthDayMaster = new BirthDayMaster();
        BeanUtils.copyProperties(birthDayRequest,birthDayMaster);
        try {
            birthDayMaster.setDate(new Date());
            this.birthDayRepository.save(birthDayMaster);
            mainResponse.setMessage("Birthday added successfully");
            mainResponse.setResponseCode(HttpStatus.OK.value());
            mainResponse.setFlag(true);
        }catch (Exception e){
            e.printStackTrace();
            mainResponse.setMessage("Something went wrong");
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            mainResponse.setFlag(false);
        }
        return mainResponse;
    }

    @Override
    public MainResponse update(BirthDayRequest birthDayRequest) {
        MainResponse mainResponse = new MainResponse();
        Optional<BirthDayMaster> birthDayMaster =this.birthDayRepository.findById(birthDayRequest.getBirthDayId());

        try {
            BeanUtils.copyProperties(birthDayRequest,birthDayMaster.get());
            birthDayMaster.get().setDate(new Date());
            this.birthDayRepository.save(birthDayMaster.get());
            mainResponse.setMessage("Birthday updated successfully");
            mainResponse.setResponseCode(HttpStatus.OK.value());
            mainResponse.setFlag(true);
        }catch (Exception e){
            e.printStackTrace();
            mainResponse.setMessage("Something went wrong");
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            mainResponse.setFlag(false);
        }
        return mainResponse;
    }

    @Override
    public BirthDayMaster birthdayDetails(Integer birthDayId) {
        BirthDayMaster birthDayMaster = this.birthDayRepository.findById(birthDayId).get();
        return birthDayMaster;
    }

    @Override
    public MainResponse delete(Integer birthDayId) {
        MainResponse mainResponse = new MainResponse();
        try {
            this.birthDayRepository.deleteById(birthDayId);
            mainResponse.setMessage("Birthday deleted successfully");
            mainResponse.setResponseCode(HttpStatus.OK.value());
            mainResponse.setFlag(true);
        }catch (Exception e){
            e.printStackTrace();
            mainResponse.setMessage("Something went wrong");
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            mainResponse.setFlag(false);
        }
        return mainResponse;
    }

    @Override
    public List<BirthDayMaster> allBirthDays() {
        List<BirthDayMaster> birthDayMasters = this.birthDayRepository.findAll();
        return birthDayMasters;
    }
}
