package click.dailyfeed.deadletter.domain.deadletter.mapper;

import click.dailyfeed.code.domain.activity.dto.MemberActivityDto;
import click.dailyfeed.code.domain.activity.type.MemberActivityType;
import click.dailyfeed.deadletter.domain.deadletter.document.KafkaListenerDeadLetterDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaListenerMapper {
    private final ObjectMapper objectMapper;

    public KafkaListenerDeadLetterDocument fromPostRequest(MemberActivityDto.PostActivityRequest request) throws JsonProcessingException {
        String strPayload = objectMapper.writeValueAsString(request);
        return KafkaListenerDeadLetterDocument.newDeadLetter(strPayload, MemberActivityType.Category.POST);
    }

    public KafkaListenerDeadLetterDocument fromCommentRequest(MemberActivityDto.CommentActivityRequest request) throws JsonProcessingException {
        String strPayload = objectMapper.writeValueAsString(request);
        return KafkaListenerDeadLetterDocument.newDeadLetter(strPayload, MemberActivityType.Category.COMMENT);
    }

    public KafkaListenerDeadLetterDocument fromPostLikeRequest(MemberActivityDto.PostLikeActivityRequest request) throws JsonProcessingException {
        String strPayload = objectMapper.writeValueAsString(request);
        return KafkaListenerDeadLetterDocument.newDeadLetter(strPayload, MemberActivityType.Category.POST_LIKE);
    }

    public KafkaListenerDeadLetterDocument fromCommentLikeRequest(MemberActivityDto.CommentLikeActivityRequest request) throws JsonProcessingException {
        String strPayload = objectMapper.writeValueAsString(request);
        return KafkaListenerDeadLetterDocument.newDeadLetter(strPayload, MemberActivityType.Category.COMMENT_LIKE);
    }
}
