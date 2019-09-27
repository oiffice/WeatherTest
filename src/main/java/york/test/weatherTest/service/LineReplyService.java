package york.test.weatherTest.service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.LineBotProperties;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.stereotype.Service;
import york.test.weatherTest.config.LineProperties;

import javax.inject.Inject;

@Service
@LineMessageHandler
public class LineReplyService {


    @Inject
    private LineBotProperties lineBotProperties;
    @Inject
    private LineProperties lineProperties;

    @EventMapping
    public TextMessage handleTextMessagEvent(MessageEvent<TextMessageContent> event) {

        String msgText = event.getMessage().getText();

        System.out.println(msgText);

        if (msgText.toLowerCase().contains("john")) {

            final LineMessagingClient client =
                    LineMessagingClient.builder(lineBotProperties.getChannelToken())
                            .build();

            String url = "https://dl.dropboxusercontent.com/s/ib79a3wtfm5uw31/john.jpg";
            ImageMessage imageMessage = new ImageMessage(url, url);
            ImageMessage.ImageMessageBuilder imageMessageBuilder = imageMessage.toBuilder();


            final ReplyMessage replyMessage = new ReplyMessage(event.getReplyToken(), imageMessageBuilder.build());
            client.replyMessage(replyMessage);

        }

        return null;

    }
}