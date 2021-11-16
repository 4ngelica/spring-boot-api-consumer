package com.example.api_consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@SpringBootApplication
public class PostLogin {

	public static void main(String[] args) throws IOException, InterruptedException {

		var values = new HashMap<String, String>() {{
			put("email", "angel@teste.com");
			put ("password", "12345678");
		}};

		var objectMapper = new ObjectMapper();
		String requestBody = objectMapper
				.writeValueAsString(values);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://laravel-car-rental-api.herokuapp.com/api/login"))
				.setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();

		HttpResponse<String> response = client.send(request,
				HttpResponse.BodyHandlers.ofString());

		System.out.println(response.body());
	}
}
