package hicc.toy.services;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.exception.CustomException;
import hicc.toy.exception.ErrorCode;
import hicc.toy.repository.ArticleRepository;
import hicc.toy.web.dto.ArticleRequestDto;
import hicc.toy.web.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        return articleRepository
                .save(requestDto.toEntity())
                .getId();
    }

    /*
     * 게시글 리스트 조회 - (게시글 종류, 삭제 여부기준)
     * */
    @Transactional
    public Page<ArticleResponseDto> findByArticleTypeAndDeleteYn(ArticleType articleType, boolean deleteYn, Pageable pageable) {
        Page<Article> page = articleRepository.findByArticleTypeAndDeleteYn(articleType, deleteYn, pageable);
        List<ArticleResponseDto> articles = page
                .getContent()
                .stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(articles, pageable, page.getTotalElements());
    }

    @Transactional
    public Page<ArticleResponseDto> searchArticles(String title, ArticleType articleType, boolean deleteYn, Pageable pageable) {
        Page<Article> page = articleRepository.findByTitleContainingAndArticleTypeAndDeleteYn(title, articleType, deleteYn, pageable);
        List<ArticleResponseDto> articles = page.getContent()
                .stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(articles, pageable, page.getTotalElements());
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
