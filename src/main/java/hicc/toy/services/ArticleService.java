package hicc.toy.services;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.exception.CustomException;
import hicc.toy.exception.ErrorCode;
import hicc.toy.repository.ArticleRepository;
import hicc.toy.web.dto.ArticleRequestDto;
import hicc.toy.web.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public Long save(final ArticleRequestDto requestDto) {
        return articleRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<ArticleResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "writtenDate");
        List<Article> list = articleRepository.findAll(sort);
        return list.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }

    /*
     * 게시글 리스트 조회 - (삭제 여부 기준)
     * */
    public List<ArticleResponseDto> findAllByDeleteYn(final char deleteYn) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Article> list = articleRepository.findAllByDeleteYn(deleteYn, sort);
        return list.stream().map(ArticleResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ArticleResponseDto findById(final Long id) {
        Article entity = articleRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        return new ArticleResponseDto(entity);
    }

    @Transactional
    public Long update(final Long id, final ArticleRequestDto requestDto) {
        Article entity = articleRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(requestDto.getArticleType(), requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public Long delete(final Long id) {
        Article entity = articleRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.delete();
        return id;
    }
}
