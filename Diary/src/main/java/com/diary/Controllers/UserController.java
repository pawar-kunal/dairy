package com.diary.Controllers;

import com.diary.Payloads.Requests.*;
import com.diary.Payloads.Responses.MainResponse;
import com.diary.Payloads.Responses.SignInResponse;
import com.diary.Payloads.Responses.UserDetailsResponse;
import com.diary.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpRequest signUpRequest){
        MainResponse mainResponse = this.userService.signup(signUpRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody SignInRequest signInRequest){
        SignInResponse  signInResponse = this.userService.signin(signInRequest);
        return new ResponseEntity(signInResponse, HttpStatus.OK);
    }

    @GetMapping("/user-details/{id}")
    public ResponseEntity userDetails(@PathVariable("id") Long id){
        UserDetailsResponse userDetails = this.userService.userDetails(id);
        return new ResponseEntity(userDetails, HttpStatus.OK);
    }

    @PutMapping("/update-profile")
    public ResponseEntity updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest){
        MainResponse mainResponse = this.userService.updateProfile(updateProfileRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest){
        MainResponse mainResponse = this.userService.updatePassword(updatePasswordRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        MainResponse mainResponse = this.userService.forgotPassword(forgotPasswordRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity verifyOTP(@RequestBody VerifyOTPRequest verifyOTPRequest){
        MainResponse mainResponse = this.userService.verifyOTP(verifyOTPRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new-password")
    public ResponseEntity newPassword(@RequestBody NewPasswordRequest newPasswordRequest){
        MainResponse mainResponse = this.userService.newPassword(newPasswordRequest);
        if (Boolean.TRUE.equals(mainResponse.getFlag())){
            return new ResponseEntity(mainResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity(mainResponse, HttpStatus.BAD_REQUEST);
        }
    }




}
