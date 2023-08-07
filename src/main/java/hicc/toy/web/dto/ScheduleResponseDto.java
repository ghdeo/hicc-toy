package hicc.toy.web.dto;

import hicc.toy.domain.schedule.Schedule;
import hicc.toy.domain.schedule.ScheduleType;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private ScheduleType scheduleType;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.scheduleType = schedule.getScheduleType();
        this.content = schedule.getContent();
    }
}
