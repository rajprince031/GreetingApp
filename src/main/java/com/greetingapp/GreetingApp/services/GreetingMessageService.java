package com.greetingapp.GreetingApp.services;


import com.greetingapp.GreetingApp.dto.GreetingMessageDTO;
import com.greetingapp.GreetingApp.entities.GreetingMessageEntity;
import com.greetingapp.GreetingApp.repositories.GreetingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GreetingMessageService {

    final ModelMapper modelMapper;
    final GreetingRepository greetingRepository;

    public GreetingMessageService(ModelMapper modelMapper, GreetingRepository greetingRepository) {
        this.modelMapper = modelMapper;
        this.greetingRepository = greetingRepository;
    }

    public GreetingMessageDTO createMessage(GreetingMessageDTO greetingMessageDTO){
        GreetingMessageEntity greetingMessageEntity = modelMapper.map(greetingMessageDTO , GreetingMessageEntity.class);
        return modelMapper.map(greetingRepository.save(greetingMessageEntity),GreetingMessageDTO.class);
    }

    public List<GreetingMessageDTO> getAllGreetings() {

        return greetingRepository
                .findAll()
                .stream()
                .map(greetingMessageEntity -> modelMapper.map(greetingMessageEntity, GreetingMessageDTO.class))
                .collect(Collectors.toList());
    }

    public String getMessageById(Integer id) {
        GreetingMessageEntity greetingMessageEntity =  greetingRepository.findById(id).orElse(null);
        if(greetingMessageEntity == null) return "No Messages";
        return greetingMessageEntity.getMessage();
    }

    public List<String> getAllMessages() {
        return greetingRepository
                .findAll()
                .stream()
                .map(greetingMessageEntity1 -> greetingMessageEntity1.getMessage())
                .collect(Collectors.toList());
    }

    public GreetingMessageDTO modifyMessage(Integer id, String message) {
        GreetingMessageEntity greetingMessageEntity = greetingRepository.findById(id).orElse(null);
        if(greetingMessageEntity == null) return null;
        greetingMessageEntity.setMessage(message);
        return modelMapper.map(greetingMessageEntity,GreetingMessageDTO.class);
    }

    public boolean deleteMessage(Integer id) {
        greetingRepository.deleteById(id);
        if(greetingRepository.findById(id).orElse(null) != null) return false;
        return true;
    }
}
