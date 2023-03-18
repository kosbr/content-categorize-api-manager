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

    /**
     * Sends event to the rabbit. The consumer of this event must open the URL and read the text from the webpage.
     * @param taskId
     * @param url
     */
    public void sendEventToParse(UUID taskId, String url) {
        // this line is still inside transaction. I don't want the event to be sent now, because transaction may be
        // rolled back, and I'll lose consistency.
        // to be honest this is not a silver bullet, but in reduces probability of inconsistency.
        DBTransactionUtils.executeAfterTransactionCompleted(() -> {
            ParseTaskEvent event = new ParseTaskEvent();
            event.setUrl(url);
            event.setTaskId(taskId);
            streamBridge.send(contentExchange, MessageBuilder.withPayload(event).build());
        });
    }
}
