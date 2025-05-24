package kakaotechcampus.scheduleproject.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private Long authorId;
    private LocalDate modifiedAt;
}
