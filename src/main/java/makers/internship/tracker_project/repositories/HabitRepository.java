package makers.internship.tracker_project.repositories;

import makers.internship.tracker_project.entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
}
