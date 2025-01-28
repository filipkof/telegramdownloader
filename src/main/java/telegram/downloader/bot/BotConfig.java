package telegram.downloader.bot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Data
@Configuration
public class BotConfig {

    @Value("${bot.token}")
    private String botToken;

    @Bean
    public TelegramClient getTelegramClient() {
        return new OkHttpTelegramClient(botToken);
    }
}
