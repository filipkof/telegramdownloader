package telegram.downloader.bot.util;


import com.codeborne.selenide.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


@Slf4j
@Service
@RequiredArgsConstructor
public class Helpers {

    public String instUrlFinder(String instUrl) {

        var options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Linux; arm_64; Android 15; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.6723.98 YaBrowser/24.12.1.98.00 SA/3 Mobile Safari/537.36");
        Configuration.browserCapabilities = options;
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "128.0";

        open(instUrl);
        var videoElement = $x("//video[@src]");
        return videoElement.getAttribute("src");
    }
}
