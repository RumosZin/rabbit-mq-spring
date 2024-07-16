package com.rabbit.demo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private String title;
    private String content;
}
