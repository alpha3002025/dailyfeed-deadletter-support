package click.dailyfeed.deadletter.domain.deadletter.mapper;

import click.dailyfeed.code.domain.activity.dto.MemberActivityDto;
import click.dailyfeed.code.domain.activity.type.MemberActivityType;
import click.dailyfeed.deadletter.domain.deadletter.document.KafkaPublisherDeadLetterDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaPublisherMapper {
    private final ObjectMapper objectMapper;

    public KafkaPublisherDeadLetterDocument fromPostRequest(MemberActivityDto.PostActivityRequest request) throws JsonProcessingException {
        String strPayload = objectMapper.writeValueAsString(request);
        return KafkaPublisherDeadLetterDocument.newDeadLetter(strPayload, MemberActivityType.Category.POST);
    }

    public KafkaPublisherDeadLetterDocument fromCommentRequest(MemberActivityDto.CommentActivityRequest request) throws JsonProcessingException {
        String strPayload = objectMapper.writeValueAsString(request);
        return KafkaPublisherDeadLetterDocument.newDeadLetter(strPayload, MemberActivityType.Category.COMMENT);
    }

    public KafkaPublisherDeadLetterDocument fromPostLikeRequest(MemberActivityDto.PostLikeActivityRequest request) throws JsonProcessingException {
        String strPayload = objectMapper.writeValueAsString(request);
        return KafkaPublisherDeadLetterDocument.newDeadLetter(strPayload, MemberActivityType.Category.POST_LIKE);
    }

    public KafkaPublisherDeadLetterDocument fromCommentLikeRequest(MemberActivityDto.CommentLikeActivityRequest request) throws JsonProcessingException {
        String strPayload = objectMapper.writeValueAsString(request);
        return KafkaPublisherDeadLetterDocument.newDeadLetter(strPayload, MemberActivityType.Category.COMMENT_LIKE);
    }
}
