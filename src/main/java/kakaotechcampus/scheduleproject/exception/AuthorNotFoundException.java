package kakaotechcampus.scheduleproject.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long authorId) {
        super("작성자 ID " + authorId + "에 해당하는 작성자가 존재하지 않습니다.");
    }
}
