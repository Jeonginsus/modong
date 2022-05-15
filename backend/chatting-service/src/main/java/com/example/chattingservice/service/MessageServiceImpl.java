package com.example.chattingservice.service;

import com.example.chattingservice.data.entity.MessageEntity;
import com.example.chattingservice.data.entity.UserEntity;
import com.example.chattingservice.repository.MessageRepository;
import com.example.chattingservice.data.dto.MessageDto;
import com.example.chattingservice.data.dto.RoomDto;
import com.example.chattingservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository msgRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<MessageDto> getMessageList(Long roomId) {
        // 매퍼생성
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // 조회
        List<MessageEntity> list =  msgRepository.findByRoomId(roomId).get();
        List<MessageDto> res = new ArrayList<>();
        for (MessageEntity entity:list) {
            MessageDto msg = mapper.map(entity, MessageDto.class);
//            msg.setDate(entity.getDate());
            // ^^username조회->나중에 join해서 가져오는걸로 refactoring
            List<UserEntity> user = userRepository.findByUserId(entity.getUserId()).get();
            if(!user.isEmpty()) msg.setUserName(user.get(0).getUserName());
            res.add(msg);
        }

        return res;
    }

    @Override
    public boolean addMessage(MessageDto dto) {
        // 매퍼생성
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        try{
            MessageEntity entity = mapper.map(dto, MessageEntity.class);
            msgRepository.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}