import Members.TeamMember;
import Team.Team;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        URI uri = URI.create("http://ya.praktikum.fvds.ru:8080/dev-day/register");
        Team team = new Team();
        team.setName("A-team");
        team.setGitHubUrl("url");


        TeamMember varvara = new TeamMember("Matoi74r@yandex.ru", "cohort_...", "Varvara",
                "Matveeva");
        TeamMember egor = new TeamMember("riddik24@yandex.ru", "cohort_...", "Egor",
                "Yakovlev");
        TeamMember eugene = new TeamMember("kavylkin@gmail.com", "cohort_...", "Eugene",
                "Kavylkin");
        TeamMember vladimir = new TeamMember("vv.bakh@yandex.ru", "cohort_32", "Vladimir",
                "Bakhanovich");
        team.getPartners().add(varvara);
        team.getPartners().add(egor);
        team.getPartners().add(eugene);
        team.getPartners().add(vladimir);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        System.out.println(gson.toJson(team));
    }
}
