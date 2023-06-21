package hicc.toy.web;

import hicc.toy.services.CommentService;
import hicc.toy.web.dto.CommentRequestDto;
import hicc.toy.web.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/article/{id}/comments")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long save(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.save(requestDto);
    }

    @GetMapping("/article/{id}/comments")
    public List<CommentResponseDto> findByArticleId(@PathVariable Long id) {
        return commentService.findByArticleId(id);
    }

    @PutMapping("/article/{articleId}/comments/{commentId}")
    public Long update(@PathVariable final Long articleId,
                       @PathVariable final Long commentId,
                       @RequestBody CommentRequestDto requestDto) {
        commentService.update(commentId, requestDto);
        return commentId;
    }

    @DeleteMapping("/article/{articleId}/comments/{commentId}")
    public Long delete(@PathVariable final Long articleId, @PathVariable final Long commentId) {
        commentService.delete(commentId);
        return commentId;
    }
}
