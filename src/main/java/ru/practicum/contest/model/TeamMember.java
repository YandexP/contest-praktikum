package ru.practicum.contest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMember {
    private String email;

    private String cohort;

    private String firstName;

    private String lastName;
}
