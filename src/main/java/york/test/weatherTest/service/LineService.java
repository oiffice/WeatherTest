package york.test.weatherTest.service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.LineBotProperties;
import org.springframework.stereotype.Service;
import york.test.weatherTest.config.LineProperties;

import javax.inject.Inject;

@Service
public class LineService {

    @Inject
    private LineBotProperties lineBotProperties;
    @Inject
    private LineProperties lineProperties;

    public void sendResultToLine(String weatherResult) {

        final LineMessagingClient client =
                LineMessagingClient.builder(lineBotProperties.getChannelToken())
                .build();

        final TextMessage textMessage = new TextMessage(weatherResult);

        lineProperties.getIds().forEach(id -> {
            final PushMessage pushMessage = new PushMessage(id, textMessage);
            client.pushMessage(pushMessage);
        });



    }
}
