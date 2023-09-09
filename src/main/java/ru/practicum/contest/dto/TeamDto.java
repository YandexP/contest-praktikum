package ru.practicum.contest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDto {
    private String name;

    private String gitHubUrl;

    private List<TeamMemberDto> participants;
}
