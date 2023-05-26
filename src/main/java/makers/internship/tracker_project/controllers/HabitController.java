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
    public HabitDto postHabit(@RequestBody HabitDto requestBody){
        return service.postHabit(requestBody);
    }

    @GetMapping("/update")
    public HabitDto getUpdateHabit(@RequestParam String name, @RequestParam String username){
        return service.getHabit(name, username);
    }

    @PutMapping("/update")
    public HabitDto putUpdateHabit(@RequestBody HabitDto requestBody){
        return service.putHabit(requestBody);
    }
    @PutMapping("/add")
    public HabitDto addHabitToMyList(@RequestParam String name, @RequestParam String username){
        return service.addHabitToMyList(name, username);
    }


    @GetMapping()
    public List<HabitDto> getAllHabit(){
        return service.getHabits();
    }

    @GetMapping("/{username}")
    public List<HabitDto> getAllHabitForUser(@PathVariable("username") String username){
        return service.getHabitsForUser(username);
    }

    @DeleteMapping()
    public void deleteHabitFromUser (@RequestParam Long habitId, @RequestParam String username){
        service.deleteHabit(habitId, username);
    }

    @DeleteMapping("/bulk/")
    public void deleteAllHabits (@RequestParam("userId") Long userId){
        service.deleteAllHabits(userId);
    }
}
