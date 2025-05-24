package kakaotechcampus.scheduleproject.service;

import kakaotechcampus.scheduleproject.dto.schedule.*;
import kakaotechcampus.scheduleproject.entity.Schedule;
import kakaotechcampus.scheduleproject.exception.AuthorNotFoundException;
import kakaotechcampus.scheduleproject.exception.PasswordMismatchException;
import kakaotechcampus.scheduleproject.exception.ScheduleNotFoundException;
import kakaotechcampus.scheduleproject.repository.AuthorRepository;
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
    private final AuthorRepository authorRepository;

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

    public List<ScheduleResponseDto> getAllSchedulesByModifiedDateAndAuthor(Long authorId, String modifiedDate) throws SQLException {
        if (authorRepository.existsAuthorById(authorId)) {
            throw new AuthorNotFoundException(authorId);
        }
        List<Schedule> schedules = scheduleRepository.findAllByModifiedDateAndAuthor(authorId, modifiedDate);
        if (schedules.isEmpty()) {
            throw new ScheduleNotFoundException(modifiedDate);
        }
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
        if (schedule == null) {
            throw new ScheduleNotFoundException(id);
        }
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
            throw new ScheduleNotFoundException(id);
        }
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new PasswordMismatchException();
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
            throw new ScheduleNotFoundException(id);
        }
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new PasswordMismatchException();
        }
        scheduleRepository.delete(id);
    }
}
