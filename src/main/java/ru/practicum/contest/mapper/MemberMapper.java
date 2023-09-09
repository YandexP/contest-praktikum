package ru.practicum.contest.mapper;

import org.mapstruct.Mapper;
import ru.practicum.contest.dto.TeamMemberDto;
import ru.practicum.contest.model.TeamMember;

@Mapper
public class MemberMapper {
    public TeamMemberDto toDto(TeamMember member) {
        if(member == null) {
            return null;
        }

        return new TeamMemberDto(member.getEmail(), member.getCohort(), member.getFirstName(), member.getLastName());
    }

    public TeamMember toModel(TeamMemberDto memberDto) {
        if(memberDto == null) {
            return null;
        }

        return new TeamMember(memberDto.getEmail(), memberDto.getCohort(), memberDto.getFirstName(),
                memberDto.getLastName());
    }
}
