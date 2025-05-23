package kakaotechcampus.scheduleproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleUpdateRequestDto {
    private String title;
    private String author;
    private String password;
}
