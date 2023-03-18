package com.github.kosbr.categorize.api.contoller;

import com.github.kosbr.categorize.api.dto.CategorizeRequest;
import com.github.kosbr.categorize.api.dto.CategorizeResult;
import com.github.kosbr.categorize.api.dto.AcceptTaskResponse;
import com.github.kosbr.categorize.api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorize")
@Tag(name = "Controller to categorize web pages")
@AllArgsConstructor
public class CategorizeController {

    @Autowired
    private final TaskService taskService;

    @RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "Create task to categorize")
    @ResponseStatus(HttpStatus.CREATED)
    public AcceptTaskResponse acceptTask(@RequestBody CategorizeRequest request) {
        return taskService.acceptTask(request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get result of the task by id")
    @ResponseStatus(HttpStatus.OK)
    public CategorizeResult getResult(@PathVariable UUID id) {
        return taskService.getResult(id);
    }

}
