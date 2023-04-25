package dtos;

import entities.Facts;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FactDTO    {
    private String fact;
    private List<String> users;


    public FactDTO(Facts fact) {
        this.fact = fact.getFact();
        if(fact.getUsers() != null){
            this.users = new ArrayList<>();
            fact.getUsers().forEach(user->this.users.add(user.getUserName()));
        }
    }

    public FactDTO(String fact) {
        this.fact = fact;
    }

    public static List<FactDTO> getDtos(List<Facts> facts){
        return facts.stream().map(fact -> new FactDTO(fact)).collect(Collectors.toList());
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "FactDTO{" +
                "fact='" + fact + '\'' +
                ", users=" + users +
                '}';
    }
}
