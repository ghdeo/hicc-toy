package hicc.toy.services;

import hicc.toy.domain.schedule.Schedule;
import hicc.toy.repository.ScheduleRepository;
import hicc.toy.web.dto.ScheduleRequestDto;
import hicc.toy.web.dto.ScheduleResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepository;

    @AfterEach
    void afterEach() {
        scheduleRepository.deleteAll();
    }
    @Test
    void save() {
        // given
        ScheduleRequestDto requestDto = new ScheduleRequestDto("title", "content");
        // when
        Long savedId = scheduleService.save(requestDto);
        // then
        Assertions.assertThat(scheduleRepository.findById(savedId).get().getTitle()).isEqualTo("title");
    }

    @Test
    void findAll() {
        // given
        Schedule schedule1 = Schedule.builder().title("title1").content("content1").build();
        Schedule schedule2 = Schedule.builder().title("title2").content("content2").build();
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);

        // when
        List<ScheduleResponseDto> all = scheduleService.findAll();

        // then
        Assertions.assertThat(all.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void update() {
        // given
        Schedule schedule = Schedule.builder().title("title1").content("content1").build();
        Schedule savedSchedule = scheduleRepository.save(schedule);
        ScheduleRequestDto updateSchedule = new ScheduleRequestDto("new title", "new content");
        // when
        scheduleService.update(savedSchedule.getId(), updateSchedule);
        // then
        Assertions.assertThat(scheduleRepository.findById(savedSchedule.getId()).get().getTitle()).isEqualTo("new title");
    }
}