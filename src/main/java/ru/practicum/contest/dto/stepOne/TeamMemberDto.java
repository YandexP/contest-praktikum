package ru.practicum.contest.dto.stepOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMemberDto {
    private String email;

    private String cohort;

    private String firstName;

    private String lastName;
}
