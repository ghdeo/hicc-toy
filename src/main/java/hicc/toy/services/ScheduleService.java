package hicc.toy.services;

import hicc.toy.domain.schedule.Schedule;
import hicc.toy.repository.ScheduleRepository;
import hicc.toy.web.dto.ScheduleRequestDto;
import hicc.toy.web.dto.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long save(final ScheduleRequestDto requestDto) {
        return scheduleRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Schedule> list = scheduleRepository.findAll(sort);
        return list.stream().map(ScheduleResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(final Long id, final ScheduleRequestDto requestDto) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        schedule.get()
                .update(requestDto.getTitle(), requestDto.getContent(), requestDto.getScheduleType(), requestDto.getDate());
        return id;
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findByDateBetween(final int year, final int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        List<Schedule> schedules = scheduleRepository.findByDateBetween(startOfMonth, endOfMonth);

        return schedules
                .stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }
}
