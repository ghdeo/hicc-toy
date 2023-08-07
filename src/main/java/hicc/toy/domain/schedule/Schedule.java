package hicc.toy.domain.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private ScheduleType scheduleType;
    private LocalDateTime date;

    @Builder
    public Schedule(String title, String content, ScheduleType scheduleType, LocalDateTime date) {
        this.title = title;
        this.content = content;
        this.scheduleType = scheduleType;
        this.date = date;
    }

    public void update(String title, String content, ScheduleType scheduleType, LocalDateTime date) {
        this.title = title;
        this.content = content;
        this.scheduleType = scheduleType;
        this.date = date;
    }
}
