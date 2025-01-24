package com.diary.Controllers;

import com.diary.Payloads.Requests.MovieRequest;
import com.diary.Payloads.Responses.MainResponse;
import com.diary.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody MovieRequest movieRequest){
        MainResponse mainResponse = this.movieService.add(movieRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag()))
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        else
            return new ResponseEntity(mainResponse,HttpStatus.BAD_REQUEST);
    }
}
