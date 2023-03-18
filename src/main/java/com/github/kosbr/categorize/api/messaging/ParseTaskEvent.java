package com.github.kosbr.categorize.api.messaging;

import java.util.UUID;
import lombok.Data;

/**
 * Event which is produced in this service. It is sent to another service to read the text of a webpage.
 */
@Data
public class ParseTaskEvent {

    private UUID taskId;
    private String url;

}
