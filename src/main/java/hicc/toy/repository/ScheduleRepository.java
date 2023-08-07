package hicc.toy.repository;

import hicc.toy.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDateBetween(LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
