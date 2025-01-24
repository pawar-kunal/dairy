package com.diary.Services;

import com.diary.Masters.BirthDayMaster;
import com.diary.Payloads.Requests.BirthDayRequest;
import com.diary.Payloads.Responses.MainResponse;

import java.util.List;

public interface BirthDayService {
    MainResponse add(BirthDayRequest birthDayRequest);

    MainResponse update(BirthDayRequest birthDayRequest);

    BirthDayMaster birthdayDetails(Integer birthDayId);

    MainResponse delete(Integer birthDayId);

    List<BirthDayMaster> allBirthDays();
}
