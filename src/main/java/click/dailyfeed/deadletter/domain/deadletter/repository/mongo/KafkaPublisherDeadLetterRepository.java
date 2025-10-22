package click.dailyfeed.deadletter.domain.deadletter.repository.mongo;

import click.dailyfeed.deadletter.domain.deadletter.document.KafkaPublisherDeadLetterDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KafkaPublisherDeadLetterRepository extends MongoRepository<KafkaPublisherDeadLetterDocument, ObjectId> {
}
