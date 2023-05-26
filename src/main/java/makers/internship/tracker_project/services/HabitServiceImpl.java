package makers.internship.tracker_project.services;

import lombok.RequiredArgsConstructor;
import makers.internship.tracker_project.converters.HabitConverter;
import makers.internship.tracker_project.dto.HabitDto;
import makers.internship.tracker_project.entities.Habit;
import makers.internship.tracker_project.entities.User;
import makers.internship.tracker_project.exceptions.ExistEntityException;
import makers.internship.tracker_project.exceptions.ResourceNotFoundException;
import makers.internship.tracker_project.repositories.HabitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService{

    private final HabitRepository habitRepository;

    private final UserService userService;

    private final HabitConverter converter;

    @Transactional
    @Override
    public HabitDto postHabit(HabitDto requestBody) {
        Habit habit = habitRepository.findFirstByName(requestBody.getName());
        if (habit == null) {
            Habit newHabit = Habit.builder()
                    .name(requestBody.getName())
                    .user(userService.findByUsername(requestBody.getUsername()).orElseThrow())
                    .tag(requestBody.getTag())
                    .maxQuantity(requestBody.getMaxQuantity())
                    .frequency(requestBody.getFrequency())
                    .currentQuantity(0)
                    .dateStart(requestBody.getDateStart())
                    .dateDone(requestBody.getDateDone())
                    .done(false)
                    .build();
            habitRepository.saveAndFlush(newHabit);
            return converter.entityToDto(newHabit);
        }
        else {
            return converter.entityToDto(habit);
        }
    }

    @Transactional
    @Override
    public List<HabitDto> getHabits() {
        List<String> names = habitRepository.getAllHabitNames();
        List<Habit> habits = new ArrayList<>();
        for (String name : names) {
            habits.add(habitRepository.findFirstByName(name));
        }
        return habits.stream().map(converter::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public HabitDto getHabit(String name, String username) {
       User user = userService.findByUsername(username).orElseThrow(()-> new ExistEntityException("user not found"));
       Habit habit = habitRepository.getHabitByNameAndUser(name, user);
        return converter.entityToDto(habit);
    }

    @Transactional
    @Override
    public List<HabitDto> getHabitsForUser(String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(()-> new ExistEntityException("user not found"));
        return user.getHabits().stream().map(converter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public HabitDto putHabit(HabitDto requestBody) {
            return converter.entityToDto(updateHabit(requestBody));
    }

    @Transactional
    @Override
    public void deleteHabit(Long habitId, String username) {
        User user = userService.findByUsername(username).orElseThrow(()-> new ExistEntityException("user not found"));
        Set<Habit> list = habitRepository.findAllByUser(user).orElseThrow(() -> new ResourceNotFoundException("data not found"));
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new ResourceNotFoundException("data not found"));
        if (list.remove(habit)) {
            user.setHabits(list);
            userService.updateUser(user);
        }
    }

    @Override
    public void deleteAllHabits(Long userId) {
        habitRepository.deleteAllByUser(userService.findById(userId));
        habitRepository.flush();
    }
    @Transactional
    @Override
    public HabitDto addHabitToMyList(String name, String username) {
        User user = userService.findByUsername(username).orElseThrow(()-> new ExistEntityException("user not found"));
        Habit habit = habitRepository.getHabitByNameAndUser(name, user);
        if (habit == null) {
            Habit newHabit = habitRepository.getHabitByName(name);
            HabitDto habitDto = converter.entityToDto(newHabit);
            habitDto.setUsername(user.getUsername());
            habitDto.setDateStart(LocalDate.now());
            habitDto.setDateDone(LocalDate.now().plusDays(30));
            postHabit(habitDto);
            return converter.entityToDto(newHabit);
        }
        else {
            return converter.entityToDto(habit);
        }
    }

    private Habit updateHabit(HabitDto requestBody) {
        User user = userService.findByUsername(requestBody.getUsername()).orElseThrow(()-> new ExistEntityException("user not found"));
        Habit updatable = habitRepository.getHabitByNameAndUser(requestBody.getName(), user);
        updatable.setFrequency(requestBody.getFrequency());
        updatable.setCurrentQuantity(requestBody.getCurrentQuantity());
        updatable.setMaxQuantity(requestBody.getMaxQuantity());
        updatable.setDateStart(requestBody.getDateStart());
        updatable.setDateDone(requestBody.getDateDone());
        habitRepository.saveAndFlush(updatable);
        return updatable;
    }

    private void checkHabits(Long userId) {
        Set<Habit> list = habitRepository.findAllByUser(userService.findById(userId))
                .orElseThrow(() -> new ResourceNotFoundException("data not found"));
        for (Habit habit : list) {
            if(habit.getCurrentQuantity() >= habit.getMaxQuantity()){
                habit.setDone(true);
                habit.setDateDone(
                        LocalDate.now());
            }else{
                switch (habit.getFrequency()) {
                    case DAILY -> {
                        if (habit.getDateDone().plusDays(1).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDateDone(LocalDate.now().plusDays(30));
                        }
                    }
                    case WEEKLY -> {
                        if (habit.getDateDone().plusWeeks(1).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDateDone(LocalDate.now().plusWeeks(4));
                        }
                    }
                    case BIWEEKLY -> {
                        if (habit.getDateDone().plusWeeks(2).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDateDone(LocalDate.now().plusWeeks(4));
                        }
                    }
                    case TRIWEEKLY -> {
                        if (habit.getDateDone().plusWeeks(3).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDateDone(LocalDate.now().plusWeeks(4));
                        }
                    }
                    case MONTHLY -> {
                        if (habit.getDateDone().plusMonths(1).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDateDone(LocalDate.now().plusWeeks(4));
                        }
                    }
                    default -> {
                    }
                }
            }
        }
        habitRepository.saveAllAndFlush(list);
    }
}
