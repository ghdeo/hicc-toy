package hicc.toy.web;

import hicc.toy.services.ArticleService;
import hicc.toy.web.dto.ArticleRequestDto;
import hicc.toy.web.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {
    private final ArticleService articleService;

    @PostMapping("/article/")
    public Long save(@RequestBody ArticleRequestDto requestDto) {
        return articleService.save(requestDto);
    }

    @GetMapping("/article")
    public List<ArticleResponseDto> findAll(@RequestParam final char deleteYn) {
        return articleService.findAllByDeleteYn(deleteYn);
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
