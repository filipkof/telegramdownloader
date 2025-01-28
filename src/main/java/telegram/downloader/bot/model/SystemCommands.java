package telegram.downloader.bot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SystemCommands {

    START("/start"),
    MAIN_MENU("Главное меню");

    private final String value;
}
