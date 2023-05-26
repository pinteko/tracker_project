package makers.internship.tracker_project.controllers;

import lombok.RequiredArgsConstructor;
import makers.internship.tracker_project.dto.HabitDto;
import makers.internship.tracker_project.dto.HabitRequest;
import makers.internship.tracker_project.enums.Frequency;
import makers.internship.tracker_project.services.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService service;

    @PostMapping()
    public ResponseEntity<HabitDto> postHabit(@RequestBody HabitDto requestBody){
        return service.postHabit(requestBody);
    }

    @GetMapping("/update")
    public ResponseEntity <HabitDto> getUpdateHabit(@RequestParam String name, @RequestParam String username){
        return service.getHabit(name, username);
    }

    @PutMapping("/update")
    public ResponseEntity <HabitDto> putUpdateHabit(@RequestBody HabitDto requestBody){
        return service.putHabit(requestBody);
    }
    @PutMapping("/add")
    public ResponseEntity <HabitDto> addHabitToMyList(@RequestParam String name, @RequestParam String username){
        return service.addHabitToMyList(name, username);
    }


    @GetMapping()
    public ResponseEntity <List<HabitDto>> getAllHabit(){
        return service.getHabits();
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<HabitDto>> getAllHabitForUser(@PathVariable("username") String username){
        return service.getHabitsForUser(username);
    }

    @DeleteMapping()
    public ResponseEntity <Void> deleteHabitFromUser (@RequestParam Long habitId, @RequestParam String username){
        return service.deleteHabit(habitId, username);
    }

    @DeleteMapping("/bulk/")
    public ResponseEntity <Void> deleteAllHabits (@RequestParam("userId") Long userId){
        return service.deleteAllHabits(userId);
    }
}
