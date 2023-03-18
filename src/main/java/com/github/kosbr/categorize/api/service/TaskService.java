package com.github.kosbr.categorize.api.service;

import com.github.kosbr.categorize.api.converter.StatusConverter;
import com.github.kosbr.categorize.api.domain.Status;
import com.github.kosbr.categorize.api.domain.Task;
import com.github.kosbr.categorize.api.dto.AcceptTaskResponse;
import com.github.kosbr.categorize.api.dto.CategorizeRequest;
import com.github.kosbr.categorize.api.dto.CategorizeResult;
import com.github.kosbr.categorize.api.exception.TaskNotFoundException;
import com.github.kosbr.categorize.api.messaging.ExternalEventRepository;
import com.github.kosbr.categorize.api.repository.TaskRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TaskService {

    private final static Logger LOG = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repository;

    private final ExternalEventRepository externalEventRepository;

    private final StatusConverter statusConverter;

    /**
     * Accept task to categorize URL to the system.
     * @param request
     * @return
     */
    public AcceptTaskResponse acceptTask(CategorizeRequest request) {
        LOG.info("Task is going to be accepted " + request);
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setCreated(OffsetDateTime.now());
        task.setStatus(Status.PENDING.getCode());
        task.setUpdated(OffsetDateTime.now());
        task.setUrl(request.getUrl());
        repository.save(task);
        externalEventRepository.sendEventToParse(task.getId(), task.getUrl());
        return new AcceptTaskResponse(task.getId());
    }

    /**
     * Get the current status of task. It can be still processing or ready.
     * @param id
     * @return
     */
    public CategorizeResult getResult(UUID id) {
        LOG.info("Getting task by id " + id);
        Optional<Task> maybeTask = repository.findById(id);
        if (maybeTask.isEmpty()) {
            throw new TaskNotFoundException("Task not found");
        }
        CategorizeResult result = new CategorizeResult();
        Task task = maybeTask.get();
        result.setId(task.getId());
        result.setCreated(task.getCreated());
        result.setUpdated(task.getUpdated());
        result.setUrl(task.getUrl());
        Status status = Status.fromCode(task.getStatus());
        result.setStatus(statusConverter.convert(status));
        switch (status) {
            case READY :
                result.setResult(task.getCategories());
                break;
            case ERROR:
                result.setResult(task.getError());
                break;
            default:
                result.setResult(null);
        }
        return result;
    }

    /**
     * Saves the result from another service to the manager's database. Then we are able to display it to the client.
     * @param taskId
     * @param error
     * @param categories
     * @param success
     */
    public void saveResult(UUID taskId, String error, String categories, boolean success) {
        LOG.info(String.format("Got results for task %s: success=%s", taskId, success));
        Optional<Task> maybeTask = repository.findById(taskId);
        if (maybeTask.isEmpty()) {
            throw new IllegalArgumentException("Unknown task");
        }
        Task task = maybeTask.get();
        task.setCategories(categories);
        task.setError(error);
        task.setStatus(success ? Status.READY.getCode() : Status.ERROR.getCode());
        task.setUpdated(OffsetDateTime.now());
        repository.save(task);
    }

}
