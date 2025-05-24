package kakaotechcampus.scheduleproject.controller;

import kakaotechcampus.scheduleproject.dto.schedule.*;
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
    public ResponseEntity<ScheduleCreateResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) throws SQLException {
        ScheduleCreateResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedulesByModifiedDateAndAuthor(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String modifiedDate // 형식: YYYY-MM-DD
    ) throws SQLException {
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedulesByModifiedDateAndAuthor(authorId, modifiedDate);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id) throws SQLException {
        ScheduleResponseDto responseDto = scheduleService.getScheduleById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto) throws SQLException {

        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleDeleteRequestDto requestDto
    ) throws SQLException {
        scheduleService.deleteSchedule(id, requestDto);
        return ResponseEntity.ok().build();
    }
}
