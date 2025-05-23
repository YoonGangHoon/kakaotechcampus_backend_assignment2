package kakaotechcampus.scheduleproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ScheduleCreateResponseDto {
    private Long id;
    private String title;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
}
