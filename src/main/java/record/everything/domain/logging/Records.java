package record.everything.domain.logging;

import record.everything.dto.record.RecordDto;

import java.util.List;
import java.util.stream.Collectors;

public class Records {

    private final List<Record> records;

    public Records(List<Record> records) {
        this.records = records;
    }

    public List<RecordDto> makeRecordDtos() {
        return records.stream()
                .map(RecordDto::new)
                .collect(Collectors.toList());
    }
}
