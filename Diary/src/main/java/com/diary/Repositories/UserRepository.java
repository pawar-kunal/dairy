package com.diary.Repositories;

import com.diary.Masters.User;
import com.diary.Payloads.Responses.UserDetailsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMobile(String mobile);

    @Query("select new com.diary.Payloads.Responses.UserDetailsResponse(u.id,u.firstName,u.lastName,u.mobile,u.email,u.dateOfBirth,u.lastLogin) from User as u where u.id=:id")
    UserDetailsResponse userDetails(@Param("id") Long id);

    Optional<User> findByEmail(String email);
}
