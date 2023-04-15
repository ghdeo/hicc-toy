package hicc.toy.web;

import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.services.ArticleService;
import hicc.toy.web.dto.ArticleRequestDto;
import hicc.toy.web.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {
    private final ArticleService articleService;

    @PostMapping("/article")
    public Long save(@RequestBody ArticleRequestDto requestDto) {
        return articleService.save(requestDto);
    }

    @GetMapping("/article")
    public Page<ArticleResponseDto> getArticlesByArticleTypeAndDeleteYn(
            @RequestParam("articleType") ArticleType articleType,
            @RequestParam("deleteYn") char deleteYn,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleService.findByArticleTypeAndDeleteYn(articleType, deleteYn, pageable);
    }

    @GetMapping("/article/{id}")
    public ArticleResponseDto findById(@PathVariable final Long id) {
        return articleService.findById(id);
    }

    @PatchMapping("/article/{id}")
    public Long update(@PathVariable final Long id, @RequestBody ArticleRequestDto requestDto) {
        return articleService.update(id, requestDto);
    }

    @DeleteMapping("/article/{id}")
    public Long delete(@PathVariable final Long id) {
        return articleService.delete(id);
    }
}
