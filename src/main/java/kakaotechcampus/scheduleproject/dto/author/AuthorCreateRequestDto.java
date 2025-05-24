package kakaotechcampus.scheduleproject.dto.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorCreateRequestDto {
    @NotBlank(message = "작성자명은 필수 입력 항목입니다.")
    private String name;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}
