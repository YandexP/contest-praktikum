package ru.practicum.contest.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.contest.dto.TeamDto;
import ru.practicum.contest.dto.TeamMemberDto;
import ru.practicum.contest.model.Team;
import ru.practicum.contest.model.TeamMember;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = MemberMapper.class)
public class TeamMapper {

    @Autowired
    private MemberMapper memberMapper;

    public TeamDto toDto(Team team) {
        if(team == null) {
            return null;
        }

        TeamDto teamDto = new TeamDto();
        teamDto.setName(team.getName());
        teamDto.setGitHubUrl(team.getGitHubUrl());

        List<TeamMemberDto> memberDtoList = new ArrayList<>();

        for(TeamMember member : team.getTeamMembers()) {
            memberDtoList.add(memberMapper.toDto(member));
        }
        return teamDto;
    }
    public Team toModel(TeamDto teamDto) {
        if(teamDto == null) {
            return null;
        }

        Team team = new Team();
        team.setName(teamDto.getName());
        team.setGitHubUrl(teamDto.getGitHubUrl());

        List<TeamMember> memberList = new ArrayList<>();
        for(TeamMemberDto memberDto : teamDto.getTeamMembers()) {
            memberList.add(memberMapper.toModel(memberDto));
        }
        return team;
    }
}
