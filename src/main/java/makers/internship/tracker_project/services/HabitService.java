package makers.internship.tracker_project.services;

import makers.internship.tracker_project.dto.HabitDto;
import java.util.List;

public interface HabitService {

    HabitDto postHabit(HabitDto requestBody);
    List<HabitDto> getHabits();
    HabitDto getHabit(String name, String username);
    List<HabitDto> getHabitsForUser(String username);
    HabitDto putHabit(HabitDto requestBody);
    void deleteHabit(Long habitId, String username);
    void deleteAllHabits(Long userId);
    HabitDto addHabitToMyList(String name, String username);
}
