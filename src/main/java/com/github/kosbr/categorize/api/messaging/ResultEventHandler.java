package com.github.kosbr.categorize.api.messaging;

import com.github.kosbr.categorize.api.service.TaskService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResultEventHandler {

    private final static Logger LOG = LoggerFactory.getLogger(ResultEventHandler.class);


    private static final String DELIMITER = ",";
    private final TaskService taskService;

    public void handle(ResultEvent payload) {
        LOG.info("Result is ready: " + payload);
        List<String> categories = payload.getCategories();
        String joinedCategories = categories != null ? String.join(DELIMITER, categories) : null;
        taskService.saveResult(payload.getId(), payload.getError(), joinedCategories,
                payload.isSuccess());
    }
}
