package kakaotechcampus.scheduleproject.controller;

import kakaotechcampus.scheduleproject.dto.author.AuthorCreateRequestDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorCreateResponseDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorResponseDto;
import kakaotechcampus.scheduleproject.dto.author.AuthorUpdateRequestDto;
import kakaotechcampus.scheduleproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorCreateResponseDto> createAuthor(@RequestBody AuthorCreateRequestDto requestDto) {
        try{
            AuthorCreateResponseDto responseDto = authorService.createAuthor(requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id) {
        try {
            AuthorResponseDto responseDto = authorService.findById(id);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorUpdateRequestDto requestDto) {
        try {
            AuthorResponseDto responseDto = authorService.updateAuthor(id, requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
