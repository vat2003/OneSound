package com.project.shopapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedRequest {
    private String email;
    private String reason;
    private String content;
}
