package ru.practicum.contest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
    private String name;

    private String gitHubUrl;

    private List<TeamMember> participants;
}
