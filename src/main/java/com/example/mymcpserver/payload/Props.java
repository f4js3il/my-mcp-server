package com.example.mymcpserver.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Props(@JsonProperty("forecast") String forecast,@JsonProperty("periods") List<Period> periods) {
}
