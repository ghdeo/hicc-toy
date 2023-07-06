package hicc.toy.web.dto;

import hicc.toy.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {
    private String title;
    private String content;

    @Builder
    public Schedule toEntity() {
        return Schedule.builder()
                .title(title)
                .content(content)
                .build();
    }
}
