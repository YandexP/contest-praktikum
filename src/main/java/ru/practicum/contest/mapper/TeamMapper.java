package ru.practicum.contest.mapper;

import ru.practicum.contest.dto.TeamDto;
import ru.practicum.contest.dto.TeamMemberDto;
import ru.practicum.contest.model.Team;
import ru.practicum.contest.model.TeamMember;

import java.util.ArrayList;
import java.util.List;

public class TeamMapper {

    public static TeamDto toDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(team.getName());
        teamDto.setGitHubUrl(team.getGitHubUrl());

        List<TeamMemberDto> memberDtoList = new ArrayList<>();

        for (TeamMember member : team.getParticipants()) {
            memberDtoList.add(MemberMapper.toDto(member));
        }
        return teamDto;
    }

    public static Team toModel(TeamDto teamDto) {
        Team team = new Team();
        team.setName(teamDto.getName());
        team.setGitHubUrl(teamDto.getGitHubUrl());

        List<TeamMember> memberList = new ArrayList<>();
        for (TeamMemberDto memberDto : teamDto.getParticipants()) {
            memberList.add(MemberMapper.toModel(memberDto));
        }
        team.setParticipants(memberList);
        return team;
    }
}
