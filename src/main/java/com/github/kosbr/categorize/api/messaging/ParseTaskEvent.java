package com.github.kosbr.categorize.api.messaging;

import java.util.UUID;
import lombok.Data;

@Data
public class ParseTaskEvent {

    private UUID taskId;
    private String url;

}
