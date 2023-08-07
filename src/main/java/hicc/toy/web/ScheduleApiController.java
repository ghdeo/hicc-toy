package hicc.toy.web;

import hicc.toy.services.ScheduleService;
import hicc.toy.web.dto.ScheduleRequestDto;
import hicc.toy.web.dto.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleApiController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long save(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.save(requestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> findAll() {
        return scheduleService.findAll();
    }

    @PatchMapping("/schedule/{id}")
    public Long save(@PathVariable final Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.update(id, requestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> findByYearAndMonth(
            @RequestParam(value = "year") int year,
            @RequestParam(value = "month") int month
    ) {
        return scheduleService.findByDateBetween(year, month);
    }
}
