package makers.internship.tracker_project.converters;

import makers.internship.tracker_project.dto.HabitDto;
import makers.internship.tracker_project.entities.Habit;
import org.springframework.stereotype.Component;

@Component
public class HabitConverter {

    public HabitDto entityToDto(Habit habit) {
        return new HabitDto(
                habit.getName(),
                habit.getUser().getUsername(),
                habit.getTag(),
                habit.getFrequency(),
                habit.getCurrentQuantity(),
                habit.getMaxQuantity(),
                habit.getDateStart(),
                habit.getDateDone(),
                habit.isDone()
                );
    }
}
