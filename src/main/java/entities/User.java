package entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Getter
//@Setter
@Data
@NoArgsConstructor
@Table(name = "Users")
@SQLDelete(sql = "UPDATE Users SET enabled = NOT enabled where id = ?")
public class User {

    @Id
//    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String userName;

    @Column
    private Date createdAt;

    @Column(columnDefinition = "bool default true")
    private Boolean enabled;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "users_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tasks_id")
    private Set<Task> tasks;

    @ManyToOne
    private Discipline discipline;

    public User(String name, String lastName, String email, String userName, Date createdAt, Boolean enabled, HashSet<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.roles = roles;
    }


}
