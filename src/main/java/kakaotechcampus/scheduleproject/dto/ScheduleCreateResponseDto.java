package kakaotechcampus.scheduleproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ScheduleCreateResponseDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
