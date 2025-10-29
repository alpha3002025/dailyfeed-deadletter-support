package click.dailyfeed.deadletter.domain.deadletter.document;

import click.dailyfeed.code.domain.activity.type.MemberActivityType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Kafka 와 달리 Feign 은 중복수신 개념이 없기에, messageKey 로 중복체크가 필요없다. 그래서 messageKey 를 배제했다.
 */
@Getter
@NoArgsConstructor
@Document(collection = "kafka_publisher_dead_letters")
public class KafkaPublisherDeadLetterDocument {
    @Id
    private ObjectId id;
    private String payload; // jackson serialize

    @Field("is_completed")
    private Boolean isCompleted = Boolean.FALSE;

    @Field("is_editing")
    private Boolean isEditing = Boolean.FALSE;

    @Field("category")
    private MemberActivityType.Category category;

    @Field("message_key")
    private String messageKey;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;

    @PersistenceCreator
    public KafkaPublisherDeadLetterDocument(
            ObjectId id, String payload, Boolean isCompleted, Boolean isEditing,
            MemberActivityType.Category category, String messageKey, LocalDateTime createdAt, LocalDateTime updatedAt
    ){
        this.id = id;
        this.payload = payload;
        this.isCompleted = isCompleted;
        this.isEditing = isEditing;
        this.category = category;
        this.messageKey = messageKey;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Builder(builderMethodName = "newInstanceBuilder", builderClassName = "PublisherDeadLetterDocument")
    private KafkaPublisherDeadLetterDocument(String payload, MemberActivityType.Category category, String messageKey) {
        this.payload = payload;
        this.isCompleted = Boolean.FALSE;
        this.isEditing = Boolean.FALSE;
        this.category = category;
        this.messageKey = messageKey;
    }

    public static KafkaPublisherDeadLetterDocument newDeadLetter(String payload, MemberActivityType.Category category) {
        return KafkaPublisherDeadLetterDocument.newInstanceBuilder()
                .payload(payload)
                .category(category)
                .build();
    }

    public static KafkaPublisherDeadLetterDocument newDeadLetter(String messageKey, String payload, MemberActivityType.Category category) {
        return KafkaPublisherDeadLetterDocument.newInstanceBuilder()
                .messageKey(messageKey)
                .payload(payload)
                .category(category)
                .build();
    }

    public void markAsCompleted() {
        this.isCompleted = Boolean.TRUE;
    }
}
