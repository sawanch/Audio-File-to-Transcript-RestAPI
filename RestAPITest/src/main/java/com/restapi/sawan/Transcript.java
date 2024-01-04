package com.restapi.sawan;

public class Transcript {

	private String audio_url;
	private String id;
	private String status;
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAudio_url() {
		return audio_url;
	}

	public void setAudio_url(String audio_url) {
		this.audio_url = audio_url;
	}

	@Override
	public String toString() {
		return "Transcript [audio_url=" + audio_url + ", id=" + id + ", status=" + status + ", text=" + text + "]";
	}

}
