package click.dailyfeed.deadletter.domain.deadletter.repository.mongo;

import click.dailyfeed.deadletter.domain.deadletter.document.KafkaListenerDeadLetterDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KafkaListenerDeadLetterRepository extends MongoRepository<KafkaListenerDeadLetterDocument, ObjectId> {
    List<KafkaListenerDeadLetterDocument> findByIsCompletedOrderByCreatedAtDesc(Boolean isCompleted, Pageable pageable);
    List<KafkaListenerDeadLetterDocument> findByMessageKey(String messageKey);
}
