package kakaotechcampus.scheduleproject.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class Schedule {

    private Long id;

    private String title;

    private String author;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
