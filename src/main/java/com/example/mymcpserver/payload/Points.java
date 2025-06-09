package com.example.mymcpserver.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Points(@JsonProperty("properties") Props properties) {
}
