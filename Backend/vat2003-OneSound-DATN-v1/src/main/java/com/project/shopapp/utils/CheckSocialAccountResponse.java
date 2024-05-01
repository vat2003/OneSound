package com.project.shopapp.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckSocialAccountResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("quantity")
    private int quantity;
}
