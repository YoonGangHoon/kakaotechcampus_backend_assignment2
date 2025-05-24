package kakaotechcampus.scheduleproject.entity;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class Schedule {

    private Long id;

    private String title;

    private String password;

    private LocalDate createdAt;

    private LocalDate modifiedAt;

    private Long authorId;

}
