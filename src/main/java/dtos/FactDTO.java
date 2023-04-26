package dtos;

import entities.Fact;
import entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class FactDTO    {
    private int id;
    private String fact;
    private User user;


    public FactDTO(Fact f) {
        this.id = f.getId();
        this.fact = f.getFact();
        this.user = f.getUserName();

    }

    public FactDTO(String fact) {
        this.fact = fact;
    }

    public static List<FactDTO> getDtos(List<Fact> facts){
        return facts.stream().map(fact -> new FactDTO(fact)).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FactDTO{" +
                "id=" + id +
                ", fact='" + fact + '\'' +
                ", user=" + user +
                '}';
    }
}
