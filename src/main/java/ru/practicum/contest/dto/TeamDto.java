package ru.practicum.contest.dto;

import lombok.*;
import ru.practicum.contest.model.TeamMember;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDto {
    private String name;

    private String gitHubUrl;

    private List<TeamMemberDto> teamMembers;
}
