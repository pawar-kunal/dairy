package com.diary.Services;

import com.diary.Payloads.Requests.MovieRequest;
import com.diary.Payloads.Responses.MainResponse;

public interface MovieService {
    MainResponse add(MovieRequest movieRequest);
}
