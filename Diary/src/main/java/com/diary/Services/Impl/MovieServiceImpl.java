package com.diary.Services.Impl;

import com.diary.Masters.MovieMaster;
import com.diary.Payloads.Requests.MovieRequest;
import com.diary.Payloads.Responses.MainResponse;
import com.diary.Repositories.MovieRepository;
import com.diary.Services.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MainResponse add(MovieRequest movieRequest) {
        MainResponse mainResponse = new MainResponse();
        MovieMaster movieMaster = new MovieMaster();
        try {
            BeanUtils.copyProperties(movieRequest,movieMaster);
            movieMaster.setDate(new Date());
            this.movieRepository.save(movieMaster);
            mainResponse.setMessage("Movie added successfully");
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
}
