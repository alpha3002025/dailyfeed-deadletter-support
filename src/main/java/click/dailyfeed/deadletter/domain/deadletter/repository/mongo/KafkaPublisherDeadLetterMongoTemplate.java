package click.dailyfeed.deadletter.domain.deadletter.repository.mongo;

import click.dailyfeed.deadletter.domain.deadletter.document.KafkaPublisherDeadLetterDocument;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaPublisherDeadLetterMongoTemplate {

    private final MongoTemplate mongoTemplate;

    /**
     * KafkaPublisherDeadLetterDocument를 저장합니다.
     * - messageKey가 있는 경우: messageKey로 조회하여 upsert (중복 방지)
     * - messageKey가 없는 경우: 단순 insert
     *
     * @param document 저장할 KafkaPublisherDeadLetterDocument
     * @return upsert 결과 (messageKey가 있는 경우에만 의미 있음)
     */
    public UpdateResult upsertKafkaPublisherDeadLetter(KafkaPublisherDeadLetterDocument document) {
        if (document.getMessageKey() != null && !document.getMessageKey().isEmpty()) {
            // messageKey가 있는 경우: upsert로 중복 방지
            return upsertByMessageKey(document);
        } else {
            // messageKey가 없는 경우: 단순 insert
            mongoTemplate.insert(document);
            log.debug("Inserted KafkaPublisherDeadLetterDocument without messageKey: category={}, isCompleted={}",
                    document.getCategory(), document.getIsCompleted());
            return null;
        }
    }

    /**
     * messageKey로 조회하여 upsert를 수행합니다.
     * 동일한 messageKey가 있으면 업데이트, 없으면 삽입합니다.
     *
     * @param document 저장할 KafkaPublisherDeadLetterDocument
     * @return UpdateResult (matched count, modified count, upserted id 포함)
     */
    private UpdateResult upsertByMessageKey(KafkaPublisherDeadLetterDocument document) {
        Query query = new Query(Criteria.where("message_key").is(document.getMessageKey()));

        Update update = new Update()
                .set("payload", document.getPayload())
                .set("is_completed", document.getIsCompleted())
                .set("is_editing", document.getIsEditing())
                .set("category", document.getCategory())
                .set("message_key", document.getMessageKey())
                .setOnInsert("created_at", LocalDateTime.now())
                .set("updated_at", LocalDateTime.now());

        UpdateResult result = mongoTemplate.upsert(query, update, KafkaPublisherDeadLetterDocument.class);

        log.debug("Upserted KafkaPublisherDeadLetterDocument: messageKey={}, matched={}, modified={}, upsertedId={}",
                document.getMessageKey(),
                result.getMatchedCount(),
                result.getModifiedCount(),
                result.getUpsertedId());

        return result;
    }

    /**
     * 여러 KafkaPublisherDeadLetterDocument를 일괄 저장합니다.
     *
     * @param documents 저장할 KafkaPublisherDeadLetterDocument 리스트
     */
    public void upsertAll(Iterable<KafkaPublisherDeadLetterDocument> documents) {
        for (KafkaPublisherDeadLetterDocument document : documents) {
            upsertKafkaPublisherDeadLetter(document);
        }
    }
}