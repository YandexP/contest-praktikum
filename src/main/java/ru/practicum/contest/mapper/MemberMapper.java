package ru.practicum.contest.mapper;

import ru.practicum.contest.dto.stepOne.TeamMemberDto;
import ru.practicum.contest.model.TeamMember;

public class MemberMapper {
    public static TeamMemberDto toDto(TeamMember member) {
        return new TeamMemberDto(member.getEmail(), member.getCohort(), member.getFirstName(), member.getLastName());
    }

    public static TeamMember toModel(TeamMemberDto memberDto) {
        return new TeamMember(memberDto.getEmail(), memberDto.getCohort(), memberDto.getFirstName(),
                memberDto.getLastName());
    }
}
