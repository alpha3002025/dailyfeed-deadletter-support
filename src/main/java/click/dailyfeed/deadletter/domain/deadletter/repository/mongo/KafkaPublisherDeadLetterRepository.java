package click.dailyfeed.deadletter.domain.deadletter.repository.mongo;

import click.dailyfeed.deadletter.domain.deadletter.document.KafkaPublisherDeadLetterDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KafkaPublisherDeadLetterRepository extends MongoRepository<KafkaPublisherDeadLetterDocument, ObjectId> {
    List<KafkaPublisherDeadLetterDocument> findByIsCompletedOrderByCreatedAtDesc(Boolean isCompleted, Pageable pageable);
}
