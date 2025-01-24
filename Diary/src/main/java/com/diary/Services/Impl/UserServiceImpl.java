package com.diary.Services.Impl;

import com.diary.Masters.User;
import com.diary.Payloads.Requests.*;
import com.diary.Payloads.Responses.MainResponse;
import com.diary.Payloads.Responses.SignInResponse;
import com.diary.Payloads.Responses.UserDetailsResponse;
import com.diary.Repositories.UserRepository;
import com.diary.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private RandomNumberGenerator randomNumberGenerator;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public MainResponse signup(SignUpRequest signUpRequest) {
        MainResponse mainResponse = new MainResponse();
        User user = new User();
        BeanUtils.copyProperties(signUpRequest,user);
        try {
            user.setDate(new Date());
            user.setDateOfBirth(signUpRequest.getDateOfBirth());
            user.setStatus("Active");
            this.userRepository.save(user);

            mainResponse.setMessage("User created successfully");
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
    public SignInResponse signin(SignInRequest signInRequest) {
        SignInResponse signInResponse = new SignInResponse();

        Optional<User> user = this.userRepository.findByMobile(signInRequest.getMobile());
        if (user.isPresent()){
            if (signInRequest.getPassword().equalsIgnoreCase(user.get().getPassword())){
                user.get().setLastLogin(user.get().getLoginTime());
                user.get().setLoginTime(new Date());
                this.userRepository.save(user.get());
                signInResponse.setId(user.get().getId());
                signInResponse.setFirstName(user.get().getFirstName());
                signInResponse.setLastName(user.get().getLastName());
                signInResponse.setEmail(user.get().getEmail());
                signInResponse.setMobile(user.get().getMobile());
                signInResponse.setDateOfBirth(user.get().getDateOfBirth());
                signInResponse.setLastLogin(user.get().getLastLogin());

                signInResponse.setMessage("Signin Successfully");
                signInResponse.setResponseCode(HttpStatus.OK.value());
                signInResponse.setFlag(true);
            }else {
                signInResponse.setMessage("Invalid credentials. Please try again...!");
                signInResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
                signInResponse.setFlag(false);
            }
        }else{
            signInResponse.setMessage("Something went wrong");
            signInResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            signInResponse.setFlag(false);
        }
        return signInResponse;
    }

    @Override
    public UserDetailsResponse userDetails(Long id) {
        UserDetailsResponse details = this.userRepository.userDetails(id);
        return details;
    }

    @Override
    public MainResponse updateProfile(UpdateProfileRequest updateProfileRequest) {
        MainResponse mainResponse = new MainResponse();
        Optional<User> user = this.userRepository.findById(updateProfileRequest.getId());
        if (user.isPresent()){
            BeanUtils.copyProperties(updateProfileRequest,user.get());
            this.userRepository.save(user.get());
            mainResponse.setMessage("Profile updated successfully");
            mainResponse.setResponseCode(HttpStatus.OK.value());
            mainResponse.setFlag(true);
        }else {
            mainResponse.setMessage("Something went wrong. User not found");
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            mainResponse.setFlag(false);
        }
        return mainResponse;
    }

    @Override
    public MainResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        MainResponse mainResponse = new MainResponse();
        Optional<User> user = this.userRepository.findById(updatePasswordRequest.getId());
        if (user.isPresent()){
            if (updatePasswordRequest.getOldPassword().equals(user.get().getPassword())){
                user.get().setPassword(updatePasswordRequest.getNewPassword());
                this.userRepository.save(user.get());

                mainResponse.setMessage("Password updated successfully");
                mainResponse.setResponseCode(HttpStatus.OK.value());
                mainResponse.setFlag(true);

            }else {
                mainResponse.setMessage("Wrong old password entered");
                mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
                mainResponse.setFlag(false);
            }
        }else {
            mainResponse.setMessage("User not found");
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            mainResponse.setFlag(false);
        }
        return mainResponse;
    }

    @Override
    public MainResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        MainResponse mainResponse = new MainResponse();
        Optional<User> user = this.userRepository.findByEmail(forgotPasswordRequest.getEmail());

        if (user.isPresent()){
//                String otp = randomNumberGenerator.randomNumberGenerator();

            Random random = new Random();
            Integer otp = random.nextInt(9999);
            String generatedOtp = String.valueOf(otp);

                user.get().setOtp(generatedOtp);

                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("kunalpawar9970@gmail.com");
                simpleMailMessage.setSubject("One Time Password");
                simpleMailMessage.setTo(user.get().getEmail());
            simpleMailMessage.setText("Dear "+user.get().getFirstName()+ ",\n\n"+
                    "Your One-Time Password (OTP) is: " + otp + "\n\n" +
                    "Please use this OTP" +
                    "If you did not request this, please ignore this email.\n\n" +
                    "Thank you,\n");
                simpleMailMessage.setSentDate(new Date());

                javaMailSender.send(simpleMailMessage);
                this.userRepository.save(user.get());

                mainResponse.setMessage("OTP has been sent to your email");
                mainResponse.setResponseCode(HttpStatus.OK.value());
                mainResponse.setFlag(true);
        }else {
            mainResponse.setMessage("Something went wrong");
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            mainResponse.setFlag(false);
        }
        return mainResponse;
    }

    @Override
    public MainResponse verifyOTP(VerifyOTPRequest verifyOTPRequest) {
        MainResponse mainResponse = new MainResponse();
        Optional<User> user = this.userRepository.findById(verifyOTPRequest.getId());
        if (user.isPresent()){
            if (user.get().getOtp().equalsIgnoreCase(verifyOTPRequest.getOtp())){
                mainResponse.setMessage("OTP Verified Successfully");
                mainResponse.setResponseCode(HttpStatus.OK.value());
                mainResponse.setFlag(true);
            }else {
                mainResponse.setMessage("Invalid OTP entered. Please try again");
                mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
                mainResponse.setFlag(false);
            }
        }else {
            mainResponse.setMessage("User not found");
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            mainResponse.setFlag(false);
        }
        return mainResponse;
    }

    @Override
    public MainResponse newPassword(NewPasswordRequest newPasswordRequest) {
        MainResponse mainResponse = new MainResponse();
        Optional<User> user = this.userRepository.findById(newPasswordRequest.getId());
        if (user.isPresent()){
            user.get().setPassword(newPasswordRequest.getNewPassword());
            this.userRepository.save(user.get());
            mainResponse.setMessage("Password updated successfully");
            mainResponse.setResponseCode(HttpStatus.OK.value());
            mainResponse.setFlag(true);
        }else {
            mainResponse.setMessage("Something went wrong. User not found");
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            mainResponse.setFlag(false);
        }
        return mainResponse;
    }
}
