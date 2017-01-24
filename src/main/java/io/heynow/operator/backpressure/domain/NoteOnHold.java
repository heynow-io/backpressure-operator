package io.heynow.operator.backpressure.domain;

import io.heynow.stream.manager.client.model.Note;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class NoteOnHold {

    @Id
    private Long id;
    private Long streamId;



    @Column(columnDefinition = "CLOB")
    @Convert(converter = NoteConverter.class)
    private Note note;

    private Long arrivalTimestamp;

}
