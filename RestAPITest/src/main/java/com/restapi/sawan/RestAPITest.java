package com.restapi.sawan;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class RestAPITest {

	public static void main(String[] args) throws Exception {

		Transcript transcript = new Transcript();
		transcript.setAudio_url(AppUtil.AUDIO_SOURCE_FILE);

		Gson gson = new Gson();
		String jsonRequest = gson.toJson(transcript);

		HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.assemblyai.com/v2/transcript"))
				.header("Authorization", AppUtil.ASSEMBLY_AI_KEY).POST(BodyPublishers.ofString(jsonRequest)).build();

		HttpClient httpClient = HttpClient.newHttpClient();

		HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());

		System.out.println(postResponse.body());

		transcript = gson.fromJson(postResponse.body(), Transcript.class);
		System.out.println(transcript.getId());

		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
				.header("Authorization", AppUtil.ASSEMBLY_AI_KEY).build();

		while (true) {
			HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());

			transcript = gson.fromJson(getResponse.body(), Transcript.class);
			System.out.println(transcript.getStatus());

			if ("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())) {
				break;
			}
			Thread.sleep(1000);
		}

		System.out.println("Transcription Completed");
		// System.out.println(transcript.toString());
		System.out.println(transcript.getText());
	}

}
