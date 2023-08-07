package hicc.toy.web.dto;

import hicc.toy.domain.schedule.Schedule;
import hicc.toy.domain.schedule.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {
    private String title;
    private String content;
    private ScheduleType scheduleType;
    private LocalDateTime date;

    @Builder
    public Schedule toEntity() {
        return Schedule.builder()
                .title(title)
                .content(content)
                .scheduleType(scheduleType)
                .date(date)
                .build();
    }
}
