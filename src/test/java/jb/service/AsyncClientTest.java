package jb.service;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AsyncClientTest {

    @Test
    public void testSync() throws ExecutionException, InterruptedException, IOException {
        try (AsyncHttpClient asyncHttpClient = Dsl.asyncHttpClient()) {
            Request request = asyncHttpClient.prepareGet("https://httpbin.org/ip").build();
            Future<Response> response = asyncHttpClient.executeRequest(request);
            Response res = response.get();
            String responseBody = res.getResponseBody().split("\"")[3];
            assertTrue(responseBody.matches(".*\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}.*"));
        }
    }

    @Test
    public void testAsync() throws InterruptedException, IOException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        try (AsyncHttpClient asyncHttpClient = Dsl.asyncHttpClient()) {
            Request request = asyncHttpClient.prepareGet("https://httpbin.org/ip").build();
            Future<Response> response = asyncHttpClient.executeRequest(request, new AsyncCompletionHandler<Response>() {
                @Override
                public Response onCompleted(Response response) throws Exception {
                    atomicInteger.addAndGet(response.getStatusCode());
                    return null;
                }
            });
            Thread.sleep(1000);
        }
        assertEquals(200, atomicInteger.get());
    }

}
