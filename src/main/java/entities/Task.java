package entities;

import enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    public Task(String name, String description, Date startDate, Date endDate, Status status, User user ) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.user = user;
    }
}
