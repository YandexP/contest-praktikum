package ru.practicum.contest.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
    private String name;

    private String gitHubUrl;

    private List<TeamMember> teamMembers;
}
