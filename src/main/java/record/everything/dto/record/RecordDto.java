package record.everything.dto.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import record.everything.domain.logging.Record;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {

    private String exercise;
    private int count;
    private String level;
    private String msg;

    public RecordDto(Record record) {

        this.exercise = record.getExercise();
        this.count = record.getCount();
        this.level = record.getLevel();
        this.msg = record.getMsg();
    }

}
