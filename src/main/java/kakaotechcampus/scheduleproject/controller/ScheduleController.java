package kakaotechcampus.scheduleproject.controller;

import kakaotechcampus.scheduleproject.dto.ScheduleCreateRequestDto;
import kakaotechcampus.scheduleproject.entity.Schedule;
import kakaotechcampus.scheduleproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public String createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) {
        try {
            Long id = scheduleService.createSchedule(requestDto);
            return "Created schedule with ID: " + id;
        } catch (Exception e) {
            return "Failed: " + e.getMessage();
        }
    }

    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable Long id) {
        try {
            return scheduleService.getSchedule(id);
        } catch (Exception e) {
            return null;
        }
    }
}
