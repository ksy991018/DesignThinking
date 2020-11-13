package com.icemelon.scheduler.repository;

import com.icemelon.scheduler.dto.UniqueCode;
import com.icemelon.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, UniqueCode> {
}
