package com.greetingapp.GreetingApp.controllers;

import com.greetingapp.GreetingApp.dto.GreetingMessageDTO;
import com.greetingapp.GreetingApp.services.GreetingMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class GreetingMessageController{

    public static final Logger logger = LoggerFactory.getLogger(GreetingMessageController.class);
    private final GreetingMessageService greetingMessageService;

    public GreetingMessageController(GreetingMessageService greetingMessageService) {
        this.greetingMessageService = greetingMessageService;
    }

    @PostMapping
    public GreetingMessageDTO createMessage(@RequestBody GreetingMessageDTO greetingMessagesDTO){
        logger.info("Saving the request for greeting message : {}",greetingMessagesDTO.getId());
        return greetingMessageService.createMessage(greetingMessagesDTO);
    }

    @GetMapping
    public List<GreetingMessageDTO> getAllGreetings(){
        logger.info("Get the list of all the messages");
        return greetingMessageService.getAllGreetings();
    }

    @GetMapping("/{id}")
    public String getMessageById(@PathVariable Integer id){
        logger.info("Get the message : {} ",id);
        return greetingMessageService.getMessageById(id);
    }

    @GetMapping("/getAllMessages")
    public List<String> getAllMessages(){
        logger.info("Get All messages Only");
        return greetingMessageService.getAllMessages();
    }

    @PutMapping("/{id}")
    public GreetingMessageDTO modifyMessage(@PathVariable Integer id, @RequestBody String message){
        logger.info("Modify the existing message : {} ",id);
        return greetingMessageService.modifyMessage(id, message);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMessage(@PathVariable Integer id){
        logger.info("Delete the message : {} ",id);
        return greetingMessageService.deleteMessage(id);
    }
}
