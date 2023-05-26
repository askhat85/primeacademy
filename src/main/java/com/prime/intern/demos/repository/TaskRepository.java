package com.prime.intern.demos.repository;

import com.prime.intern.demos.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Modifying
    @Query("UPDATE Task t SET t.done = TRUE WHERE t.id =:id")
    public void markAsDone(@Param("id") Long id);
}
