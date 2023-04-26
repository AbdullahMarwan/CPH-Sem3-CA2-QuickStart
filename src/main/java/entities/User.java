package entities;

import dtos.QuoteDTO;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Size(max = 25)
    @Column(name = "user_name", nullable = false, length = 25)
    private String id;

    @Size(max = 255)
    @Column(name = "user_pass")
    private String userPass;

    @OneToMany(mappedBy = "userName")
    private Set<Quote> quotes = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_name"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userName")
    private Set<Fact> facts = new LinkedHashSet<>();

    public Set<QuoteDTO> getQuotesDTO(Set<Quote> quoteList) {
        Set<QuoteDTO> quoteDTOList = new LinkedHashSet<>();
        for (Quote quote : quoteList) {
            quoteDTOList.add(new QuoteDTO(quote));
        }
        return quoteDTOList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Set<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Set<Quote> quotes) {
        this.quotes = quotes;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Fact> getFacts() {
        return facts;
    }

    public void setFacts(Set<Fact> facts) {
        this.facts = facts;
    }

}