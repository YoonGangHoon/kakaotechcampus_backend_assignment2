package kakaotechcampus.scheduleproject.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(String modifiedDate) {
        super(modifiedDate + "에 일정이 존재하지 않습니다.");
    }

    public ScheduleNotFoundException(Long id) {
        super("일정 ID " + id + "에 해당하는 일정이 존재하지 않습니다.");
    }
}
