package ru.practicum.contest.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private String name;

    private String token;

    private String nextTaskUrl;
}
