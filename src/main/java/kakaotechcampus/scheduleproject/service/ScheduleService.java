package kakaotechcampus.scheduleproject.service;

import kakaotechcampus.scheduleproject.dto.ScheduleCreateRequestDto;
import kakaotechcampus.scheduleproject.dto.ScheduleCreateResponseDto;
import kakaotechcampus.scheduleproject.dto.ScheduleResponseDto;
import kakaotechcampus.scheduleproject.entity.Schedule;
import kakaotechcampus.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    public ScheduleCreateResponseDto createSchedule(ScheduleCreateRequestDto requestDto) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setTitle(requestDto.getTitle());
        schedule.setAuthor(requestDto.getAuthor());
        schedule.setPassword(requestDto.getPassword());
        schedule.setCreatedAt(now);
        schedule.setModifiedAt(now);
        return scheduleRepository.save(schedule);
    }

    public List<ScheduleResponseDto> getAllSchedules(String author, String modifiedDate) throws SQLException {
        List<Schedule> schedules = scheduleRepository.findAll(author, modifiedDate);
        return schedules.stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getAuthor(),
                        schedule.getModifiedAt().toLocalDate()
                ))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto getScheduleById(Long id) throws SQLException {
        return scheduleRepository.findById(id);
    }

}
