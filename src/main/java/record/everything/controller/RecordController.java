package record.everything.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import record.everything.domain.logging.Record;
import record.everything.domain.logging.Records;
import record.everything.dto.record.RecordDto;
import record.everything.service.RecordService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/records")
public class RecordController {

    private final RecordService recordService;

    @PostMapping(path = "/store")
    public ResponseEntity<?> store(@Validated @RequestBody RecordDto recordDto) {

        String exercise = recordDto.getExercise();
        int count = recordDto.getCount();
        String level = recordDto.getLevel();
        String msg = recordDto.getMsg();

        Record record = recordService.store(exercise, count, level, msg);

        return new ResponseEntity<>(new RecordDto(record), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(value = "exercise", required = false) String exercise,
            @RequestParam(value = "count", required = false) Integer count,
            @RequestParam(value = "level", required = false) String level,
            Pageable pageable) {

        Page<RecordDto> recordDtos = recordService.search(exercise, count, level, pageable);

        return new ResponseEntity<>(recordDtos, HttpStatus.OK);
    }

    private Records getRecordDtos(Long id) {
        List<Record> records = recordService.getAllById(id);
        return new Records(records);
    }

}
