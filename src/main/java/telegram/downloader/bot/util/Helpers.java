package telegram.downloader.bot.util;


import com.codeborne.selenide.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class Helpers {

    public String extractVideoUrl(String postUrl) {
        var client = new OkHttpClient();
        var request = new Request.Builder()
                .url("https://instagram-reels-downloader-api.p.rapidapi.com/download?url=" + postUrl)
                .get()
                .addHeader("x-rapidapi-key", "0529ebf517msh3d506bcd9fd28dap12e786jsn238900bac62b")
                .addHeader("x-rapidapi-host", "instagram-reels-downloader-api.p.rapidapi.com")
                .build();

        try (var response = client.newCall(request).execute()) {
            var jsonResponse = new JSONObject(response.body().string());
            var data = jsonResponse.getJSONObject("data");
            var medias = data.getJSONArray("medias");
            var mediaUrl = medias.getJSONObject(0);
            return mediaUrl.getString("url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String urlShorter(String longUrl) {
        var client = new OkHttpClient();
        var apiUrl = "https://tinyurl.com/api-create.php?url=" + longUrl;

        var request = new Request.Builder()
                .url(apiUrl)
                .get()
                .build();
        try (var response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public String instUrlFinder(String instUrl) {
//        var userAgent = "Mozilla/5.0 (Linux; arm_64; Android 15; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.6723.98 YaBrowser/24.12.1.98.00 SA/3 Mobile Safari/537.36";
//        try {
//            var doc = Jsoup.connect(instUrl)
//                    .userAgent(userAgent)
//                    .get();
//
//            return doc.getElementsByTag("video").get(0).attr("src");
////            return html.attr("src");
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public String instUrlFinder(String instUrl) {
        var options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Linux; arm_64; Android 15; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.6723.98 YaBrowser/24.12.1.98.00 SA/3 Mobile Safari/537.36");
        Configuration.browser = "chrome";
        Configuration.browserCapabilities = options;
        Configuration.headless = true; // Установите true, если нужно работать в headless-режиме

        open(instUrl);
        var videoElement = $x("//video[@src]");
        var videoUrl = videoElement.getAttribute("src");
        return videoUrl;
    }
}
