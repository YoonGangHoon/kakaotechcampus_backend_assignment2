package kakaotechcampus.scheduleproject.service;

import kakaotechcampus.scheduleproject.dto.author.AuthorCreateRequestDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorCreateResponseDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorResponseDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorUpdateRequestDto;
import kakaotechcampus.scheduleproject.entity.Author;
import kakaotechcampus.scheduleproject.exception.AuthorNotFoundException;
import kakaotechcampus.scheduleproject.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorCreateResponseDto createAuthor(AuthorCreateRequestDto requestDto) throws SQLException {
        LocalDate now = LocalDate.now();
        Author author = new Author();
        author.setName(requestDto.getName());
        author.setEmail(requestDto.getEmail());
        author.setCreatedAt(now);
        author.setModifiedAt(now);
        Long id = authorRepository.save(author);
        return new AuthorCreateResponseDto(id);
    }

    public AuthorResponseDto findById(Long id) throws SQLException{
        Author author = authorRepository.findById(id);
        if (author == null) {
            throw new AuthorNotFoundException(id);
        }

        return new AuthorResponseDto(author.getName(), author.getEmail());
    }

    public AuthorResponseDto updateAuthor(Long id, AuthorUpdateRequestDto requestDto) throws SQLException{
        Author author = authorRepository.findById(id);
        if (author == null) {
            throw new AuthorNotFoundException(id);
        }

        author.setName(requestDto.getName());
        author.setEmail(requestDto.getEmail());
        author.setModifiedAt(LocalDate.now());

        authorRepository.update(author);

        return new AuthorResponseDto(
                author.getName(),
                author.getEmail()
        );
    }

    public void deleteAuthor(Long id) throws SQLException{
        if (authorRepository.existsAuthorById(id)) {
            throw new AuthorNotFoundException(id);
        }
        authorRepository.delete(id);
    }
}
