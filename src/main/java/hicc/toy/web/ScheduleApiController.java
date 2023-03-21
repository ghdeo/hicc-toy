package hicc.toy.web;

import hicc.toy.services.ScheduleService;
import hicc.toy.web.dto.ScheduleRequestDto;
import hicc.toy.web.dto.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleApiController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule/save")
    public Long save(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.save(requestDto);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> findAll() {
        return scheduleService.findAll();
    }

    @PatchMapping("/schedule/{id}")
    public Long save(@PathVariable final Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.update(id, requestDto);
    }
}
