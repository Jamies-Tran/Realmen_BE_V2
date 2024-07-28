package com.capstone.realmen.repository.database.plans.weekly;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWeeklyPlanRepository extends JpaRepository<WeeklyPlanEntity, Long>{
    
}