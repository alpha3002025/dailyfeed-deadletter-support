package click.dailyfeed.deadletter.domain.deadletter.service;

import click.dailyfeed.code.domain.activity.dto.MemberActivityDto;
import click.dailyfeed.code.global.feign.exception.FeignApiSerializationFailException;
import click.dailyfeed.deadletter.domain.deadletter.document.KafkaPublisherDeadLetterDocument;
import click.dailyfeed.deadletter.domain.deadletter.mapper.KafkaPublisherMapper;
import click.dailyfeed.deadletter.domain.deadletter.repository.mongo.KafkaPublisherDeadLetterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class KafkaPublisherDeadLetterService {
    private final KafkaPublisherDeadLetterRepository kafkaPublisherDeadLetterRepository;
    private final KafkaPublisherMapper kafkaPublisherMapper;

    public void createPostActivityDeadLetter(MemberActivityDto.PostActivityRequest request){
        try{
            KafkaPublisherDeadLetterDocument document = kafkaPublisherMapper.fromPostRequest(request);
            kafkaPublisherDeadLetterRepository.save(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }

    public void createCommentActivityDeadLetter(MemberActivityDto.CommentActivityRequest request){
        try{
            KafkaPublisherDeadLetterDocument document = kafkaPublisherMapper.fromCommentRequest(request);
            kafkaPublisherDeadLetterRepository.save(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }


    public void createPostLikeActivityDeadLetter(MemberActivityDto.PostLikeActivityRequest request){
        try{
            KafkaPublisherDeadLetterDocument document = kafkaPublisherMapper.fromPostLikeRequest(request);
            kafkaPublisherDeadLetterRepository.save(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }

    public void createCommentLikeActivityDeadLetter(MemberActivityDto.CommentLikeActivityRequest request){
        try{
            KafkaPublisherDeadLetterDocument document = kafkaPublisherMapper.fromCommentLikeRequest(request);
            kafkaPublisherDeadLetterRepository.save(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }
}
