package telegram.downloader.bot.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.downloader.bot.model.SystemCommands;

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
            var userFirstName = update.getMessage().getFrom().getFirstName();

            coreActions.sendVideoToUser(chatId, helpers.instUrlFinder(message));
            closeWebDriver();

            if (message.equals(SystemCommands.START.getValue())) {
                coreActions.sendTrivialMessage(chatId, "Привет, " + userFirstName + ", ты попал в бота!");
                coreActions.sendPicToUser(chatId, "https://get.wallhere.com/photo/2048x1280-px-animals-baby-cat-cats-cute-fat-fluffy-grass-grey-kitten-kittens-1913313.jpg");
            }
        }
    }
}