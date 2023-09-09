package ru.practicum.contest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String name;

    private String token;

    private String nextTaskUrl;
}
