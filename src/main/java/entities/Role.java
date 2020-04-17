package entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Role SET enabled = NOT enabled where id = ?")
public class Role {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nameOfRole;

    @Column(columnDefinition = "bool default true")
    private Boolean enabled;

    public Role(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }

}
