package hicc.toy.domain.schedule;

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
}
