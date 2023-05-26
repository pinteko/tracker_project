package makers.internship.tracker_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import makers.internship.tracker_project.enums.Frequency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class HabitDto {

    private String name;

    private String username;

    private String tag;
    
    private Frequency frequency;

    private Integer currentQuantity;

    private Integer maxQuantity;

    private LocalDate dateStart;

    private LocalDate dateDone;

    private boolean done;
}
