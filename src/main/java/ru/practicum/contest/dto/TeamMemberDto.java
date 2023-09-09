package ru.practicum.contest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMemberDto {
    private String email;

    private Integer cohort;

    private String firstName;

    private String lastName;
}
