package com.diary.Services;

import com.diary.Payloads.Requests.*;
import com.diary.Payloads.Responses.MainResponse;
import com.diary.Payloads.Responses.SignInResponse;
import com.diary.Payloads.Responses.UserDetailsResponse;

public interface UserService {
    MainResponse signup(SignUpRequest signUpRequest);

    SignInResponse signin(SignInRequest signInRequest);

    UserDetailsResponse userDetails(Long id);

    MainResponse updateProfile(UpdateProfileRequest updateProfileRequest);

    MainResponse updatePassword(UpdatePasswordRequest updatePasswordRequest);

    MainResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

    MainResponse verifyOTP(VerifyOTPRequest verifyOTPRequest);

    MainResponse newPassword(NewPasswordRequest newPasswordRequest);
}
