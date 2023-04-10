package hicc.toy.web;

import hicc.toy.services.CommentService;
import hicc.toy.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/article/{id}/comments")
    public Long save(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.save(requestDto);
    }

    @PutMapping("/article/{articleId}/comments{commentId}")
    public Long update(@PathVariable final Long articleId,
                       @PathVariable final Long commentId,
                       @RequestBody CommentRequestDto requestDto) {
        commentService.update(commentId, requestDto);
        return commentId;
    }
}
