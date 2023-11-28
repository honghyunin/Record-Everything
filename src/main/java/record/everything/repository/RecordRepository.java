package record.everything.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import record.everything.domain.logging.Record;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByExercise(String exercise);

    List<Record> getAllById(Long id);
}
