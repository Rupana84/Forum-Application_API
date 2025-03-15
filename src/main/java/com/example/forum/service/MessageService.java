package com.example.forum.service;

import com.example.forum.model.Channel;
import com.example.forum.model.Message;
import com.example.forum.repository.ChannelRepository;
import com.example.forum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChannelRepository channelRepository;

    // Get messages by channel, return 404 if no messages exist
    public List<Message> getMessagesByChannel(Long channelId) {
        List<Message> messages = messageRepository.findByChannelId(channelId);
        if (messages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found in this channel");
        }
        return messages;
    }

    //  Find a message by ID, return 404 if not found
    public Message findMessageById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
    }

    //  Add a new message (without a channel)
    public Message addMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    //  Add a message to a specific channel, return 404 if channel not found
    public Message addMessageToChannel(Long channelId, Message message) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));

        message.setChannel(channel);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // Update an existing message, return 404 if not found
    public Message updateMessage(Long id, Message updatedMessage) {
        return messageRepository.findById(id)
                .map(existingMessage -> {
                    existingMessage.setContent(updatedMessage.getContent());
                    existingMessage.setAuthor(updatedMessage.getAuthor());
                    return messageRepository.save(existingMessage);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
    }

    // Delete a message, return 404 if not found
    public void deleteMessage(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        }
        messageRepository.deleteById(id);
    }
}