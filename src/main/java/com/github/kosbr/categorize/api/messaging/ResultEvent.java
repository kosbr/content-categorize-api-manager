package com.github.kosbr.categorize.api.messaging;

import java.util.List;
import java.util.UUID;
import lombok.Data;

/**
 * This event is consumed by this service. It contains the final result of categorization.
 */
@Data
public class ResultEvent {

    private UUID id;

    private boolean success;

    private List<String> categories;

    private String error;

}
