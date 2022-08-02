package com.digitalfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NonNull;

public class Notification {

    @NonNull
    @JsonProperty("message")
    final String message;

	public Notification(@NonNull String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}    
    
}
