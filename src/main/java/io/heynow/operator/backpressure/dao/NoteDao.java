package io.heynow.operator.backpressure.dao;

import io.heynow.operator.backpressure.domain.NoteOnHold;
import io.heynow.stream.manager.client.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDao extends JpaRepository<NoteOnHold, Long>{
}
