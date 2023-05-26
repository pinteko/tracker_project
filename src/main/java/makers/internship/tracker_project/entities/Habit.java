package makers.internship.tracker_project.entities;

import lombok.*;
import makers.internship.tracker_project.enums.Frequency;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@Table(name = "habits")
@NoArgsConstructor
@AllArgsConstructor
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="tag")
    private String tag;

    @Column(nullable = false, name="max_quantity")
    private Integer maxQuantity;

    @Column(nullable = false, name="frequency")
    @Enumerated(value=EnumType.STRING)
    private Frequency frequency;

    @Column(nullable = false, name="current_quantity")
    private Integer currentQuantity;

    @Column(name="date_start")
    private LocalDate dateStart;

    @Column(name="date_done")
    private LocalDate dateDone;

    @Column(nullable = false, name = "done")
    private boolean done;

    @Override
    public String toString() {
        return "Habit: " + name + ". " + tag;
    }
}
