package click.dailyfeed.deadletter.domain.deadletter.repository.mongo;

import click.dailyfeed.deadletter.domain.deadletter.document.KafkaListenerDeadLetterDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KafkaListenerDeadLetterRepository extends MongoRepository<KafkaListenerDeadLetterDocument, ObjectId> {
}
