package kakaotechcampus.scheduleproject.dto.schedule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleCreateRequestDto {
    private String title;
    private Long authorId;
    private String password;
}
