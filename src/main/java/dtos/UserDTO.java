package dtos;

import entities.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private String username;
    private Set<QuoteDTO> quotes;

    public UserDTO(User u) {
        this.username = u.getId();
        this.quotes = u.getQuotes();

        //if(u.getQuotes() != null)
        //   this.quotes = u.getQuotes().stream().map(q -> new QuoteDTO(q)).collect(Collectors.toList());
    }

//    public UserDTO(User u) {
//        this.username = u.getUserName();
//        this.age = u.getAge();
//        if (u.getQuotes() != null)
//            this.quotes = u.getQuotes().stream().map(q -> new QuoteDTO(q)).collect(Collectors.toList());
//    }

    public static List<UserDTO> getDtos(List<User> Users) {
        return Users.stream().map(p -> new UserDTO(p)).collect(Collectors.toList());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<QuoteDTO> getQuotes() {
        return quotes;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", quotes=" + quotes +
                '}';
    }
}
