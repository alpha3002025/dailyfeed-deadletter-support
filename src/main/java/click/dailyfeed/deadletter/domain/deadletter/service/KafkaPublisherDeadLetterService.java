package click.dailyfeed.deadletter.domain.deadletter.service;

import click.dailyfeed.code.domain.activity.dto.MemberActivityDto;
import click.dailyfeed.code.global.feign.exception.FeignApiSerializationFailException;
import click.dailyfeed.deadletter.domain.deadletter.document.KafkaPublisherDeadLetterDocument;
import click.dailyfeed.deadletter.domain.deadletter.mapper.KafkaPublisherMapper;
import click.dailyfeed.deadletter.domain.deadletter.repository.mongo.KafkaPublisherDeadLetterMongoTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service
public class KafkaPublisherDeadLetterService {
    private final KafkaPublisherDeadLetterMongoTemplate kafkaPublisherDeadLetterMongoTemplate;
    private final KafkaPublisherMapper kafkaPublisherMapper;

    public void createPostActivityDeadLetter(MemberActivityDto.PostActivityRequest request){
        try{
            KafkaPublisherDeadLetterDocument document = kafkaPublisherMapper.fromPostRequest(request);
            kafkaPublisherDeadLetterMongoTemplate.upsertKafkaPublisherDeadLetter(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }

    public void createCommentActivityDeadLetter(MemberActivityDto.CommentActivityRequest request){
        try{
            KafkaPublisherDeadLetterDocument document = kafkaPublisherMapper.fromCommentRequest(request);
            kafkaPublisherDeadLetterMongoTemplate.upsertKafkaPublisherDeadLetter(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }

    public void createPostLikeActivityDeadLetter(MemberActivityDto.PostLikeActivityRequest request){
        try{
            KafkaPublisherDeadLetterDocument document = kafkaPublisherMapper.fromPostLikeRequest(request);
            kafkaPublisherDeadLetterMongoTemplate.upsertKafkaPublisherDeadLetter(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }

    public void createCommentLikeActivityDeadLetter(MemberActivityDto.CommentLikeActivityRequest request){
        try{
            KafkaPublisherDeadLetterDocument document = kafkaPublisherMapper.fromCommentLikeRequest(request);
            kafkaPublisherDeadLetterMongoTemplate.upsertKafkaPublisherDeadLetter(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }
}
