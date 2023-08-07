package hicc.toy.domain.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    @Builder
    public Schedule(String title, String content, ScheduleType scheduleType) {
        this.title = title;
        this.content = content;
        this.scheduleType = scheduleType;
    }

    public void update(String title, String content, ScheduleType scheduleType) {
        this.title = title;
        this.content = content;
        this.scheduleType = scheduleType;
    }
}
