package record.everything.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import record.everything.domain.logging.Record;
import record.everything.dto.record.RecordDto;
import record.everything.repository.RecordQueryRepository;
import record.everything.repository.RecordRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final RecordQueryRepository recordQueryRepository;

    public Record store(String exercise, int count, String level, String msg) {
        return makeAndSaveRecord(exercise, count, level, msg);
    }

    private Record makeAndSaveRecord(String exercise, int count, String level, String msg) {
        Record record = makeRecord(exercise, count, level, msg);
        return recordRepository.save(record);
    }

    private Record makeRecord(String exercise, int count, String level, String msg) {
        return new Record(exercise, count, level, msg);
    }

    public List<Record> getAllById(Long id) {
        return recordRepository.getAllById(id);
    }

    public Page<RecordDto> search(String exercise, Integer count, String level, Pageable pageable) {
        return recordQueryRepository.search(exercise, count, level, pageable);
    }

}
