package makers.internship.tracker_project.repositories;

import makers.internship.tracker_project.entities.Habit;
import makers.internship.tracker_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    Optional<Set<Habit>> findAllByUser (User user);

//    List<Habit> findByNameDistinctByName();
    @Query(value = "SELECT DISTINCT name FROM habits", nativeQuery = true)
    List<String> getAllHabitNames();

    Habit findFirstByName(String name);
    Habit getHabitById(Long id);

    Habit getHabitByNameAndUser(String name, User user);

    Habit getHabitByName(String name);
    void deleteAllByUser(User user);
}
