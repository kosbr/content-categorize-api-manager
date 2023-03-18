package com.github.kosbr.categorize.api.repository;

import com.github.kosbr.categorize.api.domain.Task;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {
}
