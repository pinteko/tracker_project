package makers.internship.tracker_project.dto;

import lombok.Data;
import makers.internship.tracker_project.enums.Frequency;

import java.time.LocalDate;

@Data
public class HabitRequest {

    private Long id;

    private String name;

    private Long userId;

    private String tag;

    private Frequency frequency;

    private Integer currentQuantity;

    private Integer maxQuantity;

    private LocalDate date;

    private boolean done;
}
