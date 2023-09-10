package ru.practicum.contest.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Token {
    private String name;

    private String token;

    private String nextTaskUrl;
}
