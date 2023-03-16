package hicc.toy.services;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.repository.ArticleRepository;
import hicc.toy.web.dto.ArticleRequestDto;
import hicc.toy.web.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Transactional
    public Long update(final Long id, final ArticleRequestDto requestDto) {
        Optional<Article> article = articleRepository.findById(id); // 예외처리 코드 추가필요
        article.get()
                .update(requestDto.getArticleType(), requestDto.getTitle(), requestDto.getContent());
        return id;
    }
}
