package telegram.downloader.bot.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class CoreActions {

    private final TelegramClient telegramClient;

    public void sendVideoToUser(String chatId, String videoUrl) {
        var sendVideo = SendVideo.builder()
                .chatId(chatId)
                .video(new InputFile(videoUrl))
                .build();
        try {
            telegramClient.execute(sendVideo);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
