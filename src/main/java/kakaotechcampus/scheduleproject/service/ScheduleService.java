package kakaotechcampus.scheduleproject.service;

import kakaotechcampus.scheduleproject.dto.ScheduleCreateRequestDto;
import kakaotechcampus.scheduleproject.entity.Schedule;
import kakaotechcampus.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    public Long createSchedule(ScheduleCreateRequestDto requestDto) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setTitle(requestDto.getTitle());
        schedule.setAuthor(requestDto.getAuthor());
        schedule.setPassword(requestDto.getPassword());
        schedule.setCreatedAt(now);
        schedule.setModifiedAt(now);
        return scheduleRepository.save(schedule);
    }

    public Schedule getSchedule(Long id) throws SQLException {
        return scheduleRepository.findById(id);
    }

    // 수정, 삭제, 전체 조회 등 필요 시 추가
}
