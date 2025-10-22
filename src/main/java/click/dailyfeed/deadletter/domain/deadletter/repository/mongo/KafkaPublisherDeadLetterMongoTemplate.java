package click.dailyfeed.deadletter.domain.deadletter.repository.mongo;

import click.dailyfeed.deadletter.domain.deadletter.document.KafkaPublisherDeadLetterDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaPublisherDeadLetterMongoTemplate {

    private final MongoTemplate mongoTemplate;

    /**
     * KafkaPublisherDeadLetterDocument를 저장합니다.
     * Publisher는 messageKey가 없으므로 단순 insert만 수행합니다.
     *
     * @param document 저장할 KafkaPublisherDeadLetterDocument
     * @return 저장된 document
     */
    public KafkaPublisherDeadLetterDocument insertKafkaPublisherDeadLetter(KafkaPublisherDeadLetterDocument document) {
        KafkaPublisherDeadLetterDocument result = mongoTemplate.insert(document);
        log.debug("Inserted KafkaPublisherDeadLetterDocument: category={}, isCompleted={}",
                document.getCategory(), document.getIsCompleted());
        return result;
    }

    /**
     * 여러 KafkaPublisherDeadLetterDocument를 일괄 저장합니다.
     *
     * @param documents 저장할 KafkaPublisherDeadLetterDocument 리스트
     */
    public void insertAll(Iterable<KafkaPublisherDeadLetterDocument> documents) {
        for (KafkaPublisherDeadLetterDocument document : documents) {
            insertKafkaPublisherDeadLetter(document);
        }
    }
}