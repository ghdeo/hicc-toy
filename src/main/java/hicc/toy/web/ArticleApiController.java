package hicc.toy.web;

import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.services.ArticleService;
import hicc.toy.web.dto.ArticleRequestDto;
import hicc.toy.web.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
public class ArticleApiController {
    private final ArticleService articleService;

    @PostMapping("/article")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long save(@RequestBody ArticleRequestDto requestDto) {
        return articleService.save(requestDto);
    }

    @GetMapping("/article")
    public Page<ArticleResponseDto> getArticlesByArticleTypeAndDeleteYn(
            @RequestParam("articleType") ArticleType articleType,
            @RequestParam("isDeleted") boolean isDeleted,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleService.findByArticleTypeAndDeleteYn(articleType, isDeleted, pageable);
    }

    @GetMapping("/article/{id}")
    public ArticleResponseDto findById(@PathVariable final Long id) {
        return articleService.findById(id);
    }

    @GetMapping("/article/search")
    public Page<ArticleResponseDto> searchArticles(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "articleType") ArticleType articleType,
            @RequestParam(value = "isDeleted", defaultValue = "N") boolean isDeleted,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleService.searchArticles(title, articleType, isDeleted, pageable);
    }

    @GetMapping("/article/searchByDate")
    public Page<ArticleResponseDto> searchArticlesByDateRange(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate,
            @RequestParam(value = "isDeleted", defaultValue = "N") boolean isDeleted,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        if (startDate == null) {
            startDate = LocalDateTime.now().withDayOfYear(1);
        }

        if (endDate == null) {
            endDate = LocalDateTime.now();
        }

        Pageable pageable = PageRequest.of(page, size);
        return articleService.findArticlesByDateRange(startDate, endDate, isDeleted, pageable);
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
