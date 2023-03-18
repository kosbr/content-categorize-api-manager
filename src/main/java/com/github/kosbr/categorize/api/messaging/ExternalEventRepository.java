package com.github.kosbr.categorize.api.messaging;

import com.github.kosbr.categorize.api.utils.DBTransactionUtils;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExternalEventRepository {

    private final StreamBridge streamBridge;

    @Value("${configuration.content-exchange}")
    private String contentExchange;

    public ExternalEventRepository(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendEventToParse(UUID taskId, String url) {
        DBTransactionUtils.executeAfterTransactionCompleted(() -> {
            ParseTaskEvent event = new ParseTaskEvent();
            event.setUrl(url);
            event.setTaskId(taskId);
            streamBridge.send(contentExchange, MessageBuilder.withPayload(event).build());
        });
    }
}
