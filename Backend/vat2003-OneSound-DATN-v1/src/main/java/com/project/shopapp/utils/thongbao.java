package com.project.shopapp.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.entity.SingerFullInfoDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class thongbao {
    @JsonProperty("message")
    private String message;

    @JsonProperty("haha")
    private SingerFullInfoDTO SingerFullInfoDTO;
}
