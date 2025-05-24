package kakaotechcampus.scheduleproject.service;

import kakaotechcampus.scheduleproject.dto.schedule.*;
import kakaotechcampus.scheduleproject.entity.Schedule;
import kakaotechcampus.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    public ScheduleCreateResponseDto createSchedule(ScheduleCreateRequestDto requestDto) throws SQLException {
        LocalDate now = LocalDate.now();
        Schedule schedule = new Schedule();
        schedule.setTitle(requestDto.getTitle());
        schedule.setAuthorId(requestDto.getAuthorId());
        schedule.setPassword(requestDto.getPassword());
        schedule.setCreatedAt(now);
        schedule.setModifiedAt(now);
        return scheduleRepository.save(schedule);
    }

    public List<ScheduleResponseDto> getAllSchedulesByModifiedDate(String author, String modifiedDate) throws SQLException {
        List<Schedule> schedules = scheduleRepository.findAllByModifiedDate(author, modifiedDate);
        return schedules.stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getAuthorId(),
                        schedule.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto getScheduleById(Long id) throws SQLException {
        Schedule schedule = scheduleRepository.findById(id);
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .modifiedAt(schedule.getModifiedAt())
                .authorId(schedule.getAuthorId())
                .build();
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto requestDto) throws SQLException {
        Schedule schedule = scheduleRepository.findById(id);
        if (schedule == null) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
        }

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.setAuthorId(requestDto.getAuthorId());
        schedule.setTitle(requestDto.getTitle());
        schedule.setModifiedAt(LocalDate.now());

        scheduleRepository.update(schedule);

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getAuthorId(),
                schedule.getModifiedAt()
        );
    }

    public void deleteSchedule(Long id, ScheduleDeleteRequestDto requestDto) throws SQLException{
        Schedule schedule = scheduleRepository.findById(id);
        if (schedule == null) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
        }

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.delete(id);
    }
}
