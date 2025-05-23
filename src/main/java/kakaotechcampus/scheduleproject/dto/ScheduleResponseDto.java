package kakaotechcampus.scheduleproject.dto;

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
    private String author;
    private LocalDate modifiedAt;
}
