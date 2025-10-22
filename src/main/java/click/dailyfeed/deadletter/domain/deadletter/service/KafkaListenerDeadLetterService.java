package click.dailyfeed.deadletter.domain.deadletter.service;

import click.dailyfeed.code.domain.activity.dto.MemberActivityDto;
import click.dailyfeed.code.global.feign.exception.FeignApiSerializationFailException;
import click.dailyfeed.deadletter.domain.deadletter.document.KafkaListenerDeadLetterDocument;
import click.dailyfeed.deadletter.domain.deadletter.mapper.KafkaListenerMapper;
import click.dailyfeed.deadletter.domain.deadletter.repository.mongo.KafkaListenerDeadLetterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class KafkaListenerDeadLetterService {
    private final KafkaListenerDeadLetterRepository kafkaListenerDeadLetterRepository;
    private final KafkaListenerMapper kafkaListenerMapper;

    public void createPostActivityDeadLetter(MemberActivityDto.PostActivityRequest request){
        try{
            KafkaListenerDeadLetterDocument document = kafkaListenerMapper.fromPostRequest(request);
            kafkaListenerDeadLetterRepository.save(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }

    public void createCommentActivityDeadLetter(MemberActivityDto.CommentActivityRequest request){
        try{
            KafkaListenerDeadLetterDocument document = kafkaListenerMapper.fromCommentRequest(request);
            kafkaListenerDeadLetterRepository.save(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }


    public void createPostLikeActivityDeadLetter(MemberActivityDto.PostLikeActivityRequest request){
        try{
            KafkaListenerDeadLetterDocument document = kafkaListenerMapper.fromPostLikeRequest(request);
            kafkaListenerDeadLetterRepository.save(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }

    public void createCommentLikeActivityDeadLetter(MemberActivityDto.CommentLikeActivityRequest request){
        try{
            KafkaListenerDeadLetterDocument document = kafkaListenerMapper.fromCommentLikeRequest(request);
            kafkaListenerDeadLetterRepository.save(document);
        }catch (JsonProcessingException e){
            throw new FeignApiSerializationFailException();
        }
    }
}
