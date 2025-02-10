package telegram.downloader.bot.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreProcessor {

    private final CoreActions coreActions;
    private final Helpers helpers;

    public void process(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var message = update.getMessage().getText();
            var chatId = update.getMessage().getChatId().toString();

            if (message.contains("https://www.instagram.com/reel/") || message.contains("https://instagram.com/reel/")) {
                coreActions.sendVideoToUser(chatId, helpers.instUrlFinder(message));
                closeWebDriver();
            }
        }
    }
}