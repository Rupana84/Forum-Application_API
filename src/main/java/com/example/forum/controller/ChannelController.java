package com.example.forum.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import com.example.forum.model.Channel;
import com.example.forum.model.Message;
import com.example.forum.service.ChannelService;
import com.example.forum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MessageService messageService;

    // Get all channels
    @GetMapping("/")
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }

    // Get a channel by ID, return 404 if not found
    @GetMapping("/{id}")
    public ResponseEntity<Channel> getChannelById(@PathVariable Long id) {
        Channel channel = channelService.findChannelById(id);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        }
        return ResponseEntity.ok(channel);
    }

    // Create a new channel
    @PostMapping("/")
    public ResponseEntity<Channel> createChannel(@Valid @RequestBody Channel channel) {
        Channel savedChannel = channelService.createChannel(channel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedChannel);
    }

    // Delete a channel, return 404 if not found
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable Long id) {
        Channel channel = channelService.findChannelById(id);
        if (channel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        }
        channelService.deleteChannel(id);
        return ResponseEntity.ok("✅ Channel deleted successfully!");
    }

    //  Delete all channels
    @DeleteMapping("/")
    public ResponseEntity<String> deleteAllChannels() {
        channelService.deleteAllChannels();
        return ResponseEntity.ok("✅ All channels deleted successfully!");
    }

    // Add a message to a specific channel, return 404 if channel not found
    @PutMapping("/{id}")
    public ResponseEntity<Message> addMessageToChannel(@PathVariable Long id, @RequestBody Message message) {
        if (channelService.findChannelById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        }
        Message savedMessage = messageService.addMessageToChannel(id, message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }

    //  Get all messages from a specific channel, return 404 if no messages
    @GetMapping("/{id}/messages")
    public ResponseEntity<List<Message>> getMessagesFromChannel(@PathVariable Long id) {
        List<Message> messages = messageService.getMessagesByChannel(id);
        if (messages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found in this channel");
        }
        return ResponseEntity.ok(messages);
    }
}