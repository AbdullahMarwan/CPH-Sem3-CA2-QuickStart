package dtos;

import entities.Quote;
import entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class QuoteDTO {
    private int id;
    private String quote;
    private String user;

    public QuoteDTO(Quote q) {
        this.id = q.getId();
        this.quote = q.getQuote();
        this.user = String.valueOf(q.getUserName());
    }

    public QuoteDTO(String quote) {
        this.quote = quote;
    }

    public static List<QuoteDTO> getDtos(List<Quote> quotes) {
        return quotes.stream().map(q -> new QuoteDTO(q)).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "QuoteDTO{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
