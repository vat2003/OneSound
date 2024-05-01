package com.project.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class GitDTO {
    @JsonProperty("github_id")
    private String githubId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
}
