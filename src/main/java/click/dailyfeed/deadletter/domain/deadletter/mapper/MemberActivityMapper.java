package click.dailyfeed.deadletter.domain.deadletter.mapper;

import click.dailyfeed.code.domain.activity.dto.MemberActivityDto;
import click.dailyfeed.code.domain.activity.type.MemberActivityType;
import org.springframework.stereotype.Component;

@Component
public class MemberActivityMapper {
    public MemberActivityDto.PostActivityRequest newPostActivityRequest(Long memberId, Long postId, MemberActivityType activityType) {
        return MemberActivityDto.PostActivityRequest.builder()
                .memberId(memberId)
                .postId(postId)
                .activityType(activityType)
                .build();
    }

    public MemberActivityDto.PostLikeActivityRequest newPostLikeActivityRequest(Long memberId, Long postId, MemberActivityType activityType) {
        return MemberActivityDto.PostLikeActivityRequest.builder()
                .memberId(memberId)
                .postId(postId)
                .activityType(activityType)
                .build();
    }
}
