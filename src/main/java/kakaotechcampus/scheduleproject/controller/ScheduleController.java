package kakaotechcampus.scheduleproject.controller;

import kakaotechcampus.scheduleproject.dto.ScheduleCreateRequestDto;
import kakaotechcampus.scheduleproject.dto.ScheduleCreateResponseDto;
import kakaotechcampus.scheduleproject.dto.ScheduleResponseDto;
import kakaotechcampus.scheduleproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleCreateResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) {
        try {
            ScheduleCreateResponseDto responseDto = scheduleService.createSchedule(requestDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String modifiedDate // 형식: YYYY-MM-DD
    ) {
        try {
            List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules(author, modifiedDate);
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id) {
        try {
            ScheduleResponseDto responseDto = scheduleService.getScheduleById(id);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
