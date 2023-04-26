package dtos;

import entities.Quote;

import java.util.List;
import java.util.stream.Collectors;

public class QuoteDTO {
    private String quote;

    public QuoteDTO(Quote q) {
        this.quote = q.getQuote();
    }

    public QuoteDTO(String quote) {
        this.quote = quote;
    }

    public static List<QuoteDTO> getDtos(List<Quote> quotes) {
        return quotes.stream().map(q -> new QuoteDTO(q)).collect(Collectors.toList());
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "QuoteDTO{" +
                ", quote='" + quote + '\'' +
                '}';
    }
}
