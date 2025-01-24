package com.diary.Repositories;

import com.diary.Masters.BirthDayMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthDayRepository extends JpaRepository<BirthDayMaster,Integer> {
}
