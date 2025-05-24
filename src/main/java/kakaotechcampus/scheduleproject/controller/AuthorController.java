package kakaotechcampus.scheduleproject.controller;

import kakaotechcampus.scheduleproject.dto.author.AuthorCreateRequestDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorCreateResponseDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorResponseDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorUpdateRequestDto;
import kakaotechcampus.scheduleproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorCreateResponseDto> createAuthor(@RequestBody AuthorCreateRequestDto requestDto) throws SQLException  {
        AuthorCreateResponseDto responseDto = authorService.createAuthor(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id) throws SQLException {
        AuthorResponseDto responseDto = authorService.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorUpdateRequestDto requestDto) throws SQLException {
        AuthorResponseDto responseDto = authorService.updateAuthor(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) throws SQLException {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

}
