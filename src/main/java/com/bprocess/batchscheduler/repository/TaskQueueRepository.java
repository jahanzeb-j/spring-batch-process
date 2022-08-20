package com.bprocess.batchscheduler.repository;

import com.bprocess.batchscheduler.repository.entity.TaskQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskQueueRepository extends JpaRepository<TaskQueueEntity,Long> {
}
