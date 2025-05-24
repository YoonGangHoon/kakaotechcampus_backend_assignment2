package kakaotechcampus.scheduleproject.dto.author;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorUpdateRequestDto {
    private String name;
    private String email;
}
