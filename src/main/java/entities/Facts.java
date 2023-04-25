package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Facts.deleteAllRows", query = "DELETE from Facts")
@Table(name = "user_facts")
public class Facts implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "fact")
    private String fact;

    @ManyToMany(mappedBy = "userFacts")
    private List<User> users;

    public Facts() {
    }

    public Facts(String fact) {
        this.fact = fact;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }

    public List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }

    @Override
    public String toString() {
        return "Facts{" +
                "userName='" + userName + '\'' +
                ", fact='" + fact + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facts facts = (Facts) o;
        return Objects.equals(userName, facts.userName) && Objects.equals(fact, facts.fact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, fact);
    }


}
