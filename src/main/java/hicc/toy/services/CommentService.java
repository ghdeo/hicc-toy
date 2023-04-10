package hicc.toy.services;

import hicc.toy.domain.comment.Comment;
import hicc.toy.exception.CustomException;
import hicc.toy.exception.ErrorCode;
import hicc.toy.repository.ArticleRepository;
import hicc.toy.repository.CommentRepository;
import hicc.toy.repository.MemberRepository;
import hicc.toy.web.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(CommentRequestDto requestDto) {
        return commentRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto) {
        Comment entity = commentRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        entity.update(requestDto.getComment());
        return entity.getId();
    }
}

