package telegram.downloader.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.downloader.bot.util.CoreProcessor;

@Component
@RequiredArgsConstructor
public class BotMain implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final CoreProcessor coreProcessor;
    private final BotConfig botConfig;

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        coreProcessor.process(update);
    }
}
