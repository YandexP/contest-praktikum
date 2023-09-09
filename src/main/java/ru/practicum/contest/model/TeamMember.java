package ru.practicum.contest.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMember {
    private String email;

    private Integer cohort;

    private String firstName;

    private String lastName;
}
