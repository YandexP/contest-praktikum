package Team;

import Members.TeamMember;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String gitHubUrl;
    private List<TeamMember> teamMembers;

    public Team() {
        teamMembers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGitHubUrl() {
        return gitHubUrl;
    }

    public void setGitHubUrl(String gitHubUrl) {
        this.gitHubUrl = gitHubUrl;
    }

    public List<TeamMember> getPartners() {
        return teamMembers;
    }

    public void setPartners(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
