package com.optimization.loaders;


import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Slf4j
@Component
public class BooksLoader implements CommandLineRunner {
    String BASE_URL = "http://localhost:8080/api/books";
    OkHttpClient client = new OkHttpClient();

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(10000);
        runRequests();
    }

    public void runRequests() {
        int numOfThreads = 10;
        int requestsPerThread = 20;

        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < requestsPerThread; j++) {
                    try {


                        sendPostRequest(BASE_URL , "{\"title\":\"New Book\",\"author\":\"John Doe\",\"genre\":\"fiction\"}");
                        sendPostRequest(BASE_URL +"api/books", "{\"title\":\"Batman\",\"author\":\"Chris Nolan\",\"genre\":\"animation\"}");
                        sendGetRequest(BASE_URL);
                        sendGetRequest(BASE_URL + "/recommendations/fiction");
                        sendGetRequest(BASE_URL + "/recommendations/fiction");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executor.shutdown();
    }

    private void sendGetRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        log.info("Sending Get request to " + url);
        try (Response response = client.newCall(request).execute()) {
            // Process response if needed
        }
    }

    private void sendPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(url).post(body).build();
        log.info("Sending Post request to " + url);
        try (Response response = client.newCall(request).execute()) {
            // Process response if needed


        }
    }
}
