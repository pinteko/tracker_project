package makers.internship.tracker_project.services;

import makers.internship.tracker_project.dto.HabitDto;
import makers.internship.tracker_project.dto.HabitRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HabitService {

    ResponseEntity<HabitDto> postHabit(HabitDto requestBody);
    ResponseEntity<List<HabitDto>> getHabits();
    ResponseEntity<HabitDto> getHabit(String name, String username);
    ResponseEntity<List<HabitDto>> getHabitsForUser(String username);
    ResponseEntity<HabitDto> putHabit(HabitDto requestBody);
    ResponseEntity<Void> deleteHabit(Long habitId, String username);
    ResponseEntity<Void> deleteAllHabits(Long userId);
    ResponseEntity<HabitDto> addHabitToMyList(String name, String username);
}
