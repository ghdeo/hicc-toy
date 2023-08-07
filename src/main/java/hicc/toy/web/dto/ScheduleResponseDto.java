package hicc.toy.web.dto;

import hicc.toy.domain.schedule.Schedule;
import hicc.toy.domain.schedule.ScheduleType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private ScheduleType scheduleType;
    private LocalDateTime date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.scheduleType = schedule.getScheduleType();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
    }
}
