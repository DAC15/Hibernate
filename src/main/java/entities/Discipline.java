package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Discipline {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @OneToMany
    @JoinColumn
    private Set<User> users;

    @OneToOne
    @JoinColumn
    private User headOfDiscipline;

    public Discipline(String nameOfDiscipline, HashSet<User> users, User headOfDiscipline) {
        this.name = nameOfDiscipline;
        this.users = users;
        this.headOfDiscipline = headOfDiscipline;
    }

    public void setHeadOfDiscipline(User headOfDiscipline) {
        this.headOfDiscipline = headOfDiscipline;
    }
}
