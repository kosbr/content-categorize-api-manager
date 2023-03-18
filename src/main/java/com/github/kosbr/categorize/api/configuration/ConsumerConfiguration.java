package com.github.kosbr.categorize.api.configuration;

import com.github.kosbr.categorize.api.messaging.ResultEvent;
import com.github.kosbr.categorize.api.messaging.ResultEventHandler;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class ConsumerConfiguration {

    @Autowired
    private ResultEventHandler resultEventHandler;

    @Bean
    public Consumer<Message<ResultEvent>> resultChannelStreamFunction() {
        return resultEventMessage -> resultEventHandler.handle(resultEventMessage.getPayload());
    }

}
