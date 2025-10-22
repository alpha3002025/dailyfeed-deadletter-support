package click.dailyfeed.deadletter.domain.deadletter.repository.mongo;

import click.dailyfeed.deadletter.domain.deadletter.document.FeignDeadLetterDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeignDeadLetterRepository extends MongoRepository<FeignDeadLetterDocument, ObjectId> {
    List<FeignDeadLetterDocument> findByIsCompletedOrderByCreatedAtDesc(Boolean isCompleted, Pageable pageable);
}
