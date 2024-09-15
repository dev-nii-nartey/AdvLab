import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RequestSimulator {
    private static final String BASE_URL = "http://localhost:8080/api/books";
    private final HttpClient httpClient;

    public RequestSimulator() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public static void main(String[] args) {
        RequestSimulator simulator = new RequestSimulator();
        simulator.run();
    }

    public void run() {
        if (!isServerUp()) {
            System.err.println("Server is not up. Start server before simulating requests.");
            return;
        }
        System.out.println("Starting request simulation...");
        simulateRequests();
        System.out.println("Request simulation completed.");
    }

    private boolean isServerUp() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_URL))
                    .timeout(Duration.ofSeconds(5))
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.discarding());
            return true;
        } catch (ConnectException e) {
            return false;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error checking server status: " + e.getMessage());
            return false;
        }
    }

    private void simulateRequests() {
        int numOfThreads = 10;
        int requestsPerThread = 20;

        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < requestsPerThread; j++) {
                    simulatePostRequest(BASE_URL, "{\"title\":\"New Book\",\"author\":\"John Doe\",\"genre\":\"fiction\"}");
                    simulatePostRequest(BASE_URL, "{\"title\":\"Batman\",\"author\":\"Chris Nolan\",\"genre\":\"animation\"}");
                    simulateGetRequest(BASE_URL);
                    simulateGetRequest(BASE_URL + "/recommendations/fiction");
                    simulateGetRequest(BASE_URL + "/recommendations/fiction");
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.err.println("Execution interrupted: " + e.getMessage());
        }
    }

    private void simulateGetRequest(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        sendRequest(request);
    }

    private void simulatePostRequest(String url, String json) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .build();

        sendRequest(request);
    }

    private void sendRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logResponse(request, response);
        } catch (ConnectException e) {
            System.err.println("Failed to connect to the server. Ensure the server is running.");
        } catch (IOException | InterruptedException e) {
            System.err.println("Error sending request: " + e.getMessage());
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void logResponse(HttpRequest request, HttpResponse<String> response) {
        System.out.println("Request: " + request.method() + " " + request.uri());
        System.out.println("Response Status: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
        System.out.println("--------------------");
    }
}