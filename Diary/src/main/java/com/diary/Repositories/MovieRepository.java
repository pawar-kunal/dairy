package com.diary.Repositories;

import com.diary.Masters.MovieMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieMaster, Integer> {
}
