package kakaotechcampus.scheduleproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class Author {
    Long id;
    String name;
    String email;
    LocalDate createdAt;
    LocalDate modifiedAt;
}
