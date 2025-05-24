package kakaotechcampus.scheduleproject.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleCreateRequestDto {

    @NotBlank(message = "할일은 필수 입력 항목입니다.")
    @Size(max = 200, message = "할일은 200자 이내여야 합니다.")
    private String title;

    private Long authorId;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;
}
