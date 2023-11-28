package record.everything.domain.logging;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Record {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exercise;

    private int count;

    private LocalDateTime dateTime;

    private String level;

    private String msg;

    public Record(String exercise, int count, String level, String msg) {

        this.exercise = exercise;
        this.count = count;
        this.dateTime = LocalDateTime.now();
        this.level = level;
        this.msg = msg;
    }
}
