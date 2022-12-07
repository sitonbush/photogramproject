package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final EntityManager em; //모든 repository는 EntityManager를 구현해서 만들어져있는 구현체

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
       StringBuffer sb= new StringBuffer();
       sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
       sb.append("if((SELECT 1 FROM subscribe ");
       sb.append("WHERE fromUserId =? AND toUserId=u.id),1,0) subscribeState, ");
       sb.append("if((?=u.id),1,0) equalUserState ");
       sb.append("FROM user u INNER JOIN subscribe s ");
       sb.append("ON u.id = s.toUserId ");
       sb.append("WHERE s.fromUserId= ?");//세미콜론 첨부 안됨

        //1.물음표 principalID
        //2.물음표 principalID
        //3.마지막 물음표 pageUserId

        Query query= em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3,pageUserId);

        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query,SubscribeDto.class);
        return subscribeDtos;
    }


    @Transactional
    public void 구독하기(Integer fromUserId, Integer toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);

        } catch (Exception e) {
            throw new CustomApiException("이미 구독하였습니다");
        }
    }

    @Transactional
    public void 구독취소하기(Integer fromUserId, Integer toUserId) {

        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
