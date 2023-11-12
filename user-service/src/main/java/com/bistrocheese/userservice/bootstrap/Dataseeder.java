package com.bistrocheese.userservice.bootstrap;

import com.bistrocheese.userservice.model.timekeeping.Schedule;
import com.bistrocheese.userservice.model.timekeeping.Shift;
import com.bistrocheese.userservice.repository.timekeeping.ScheduleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Dataseeder implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
    private final ScheduleRepository scheduleRepository;

    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        this.loadSchedules();
    }

    private void loadSchedules() {
        List<Schedule> schedules = new ArrayList<>(
                List.of(
                        new Schedule(1, DayOfWeek.MONDAY, Shift.MORNING.ordinal()),
                        new Schedule(2, DayOfWeek.MONDAY, Shift.AFTERNOON.ordinal()),
                        new Schedule(3, DayOfWeek.MONDAY, Shift.NIGHT.ordinal()),
                        new Schedule(4, DayOfWeek.TUESDAY, Shift.MORNING.ordinal()),
                        new Schedule(5, DayOfWeek.TUESDAY, Shift.AFTERNOON.ordinal()),
                        new Schedule(6, DayOfWeek.TUESDAY, Shift.NIGHT.ordinal()),
                        new Schedule(7, DayOfWeek.WEDNESDAY, Shift.MORNING.ordinal()),
                        new Schedule(8, DayOfWeek.WEDNESDAY, Shift.AFTERNOON.ordinal()),
                        new Schedule(9, DayOfWeek.WEDNESDAY, Shift.NIGHT.ordinal()),
                        new Schedule(10, DayOfWeek.THURSDAY, Shift.MORNING.ordinal()),
                        new Schedule(11, DayOfWeek.THURSDAY, Shift.AFTERNOON.ordinal()),
                        new Schedule(12, DayOfWeek.THURSDAY, Shift.NIGHT.ordinal()),
                        new Schedule(13, DayOfWeek.FRIDAY, Shift.MORNING.ordinal()),
                        new Schedule(14, DayOfWeek.FRIDAY, Shift.AFTERNOON.ordinal()),
                        new Schedule(15, DayOfWeek.FRIDAY, Shift.NIGHT.ordinal()),
                        new Schedule(16, DayOfWeek.SATURDAY, Shift.MORNING.ordinal()),
                        new Schedule(17, DayOfWeek.SATURDAY, Shift.AFTERNOON.ordinal()),
                        new Schedule(18, DayOfWeek.SATURDAY, Shift.NIGHT.ordinal()),
                        new Schedule(19, DayOfWeek.SUNDAY, Shift.MORNING.ordinal()),
                        new Schedule(20, DayOfWeek.SUNDAY, Shift.AFTERNOON.ordinal()),
                        new Schedule(21, DayOfWeek.SUNDAY, Shift.NIGHT.ordinal())
                )
        );
        schedules.forEach(schedule -> {
            Optional<Schedule> optionalCategory = scheduleRepository.findById(schedule.getId());
            optionalCategory.ifPresentOrElse(System.out::println, () -> scheduleRepository.save(schedule));
        });
    }
}
