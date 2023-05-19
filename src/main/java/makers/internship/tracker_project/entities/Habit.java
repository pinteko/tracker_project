package makers.internship.tracker_project.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "habits")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "goal")
    private Integer goal;

    @Column(name = "rate")
    private Date rate;

    @CreationTimestamp
    @Column(name = "start")
    private LocalDateTime start;

    @UpdateTimestamp
    @Column(name = "update")
    private LocalDateTime update;

    @UpdateTimestamp
    @Column(name = "finish")
    private LocalDateTime finish;
}
