package com.bao.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageEvent {
    private String messageId;
    @JsonProperty("message_body")
    private String messageBody;
    private LocalDateTime dateTime;
}
