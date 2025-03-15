package com.example.forum.controller;

import com.example.forum.model.Message;
import com.example.forum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // ✅ Fetch all messages for a specific channel
    @GetMapping("/channel/{channelId}")
    public ResponseEntity<List<Message>> getMessagesByChannel(@PathVariable Long channelId) {
        List<Message> messages = messageService.getMessagesByChannel(channelId);
        if (messages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found in this channel");
        }
        return ResponseEntity.ok(messages);
    }

    // ✅ Fetch a single message by ID, return 404 if not found
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Message message = messageService.findMessageById(id);
        return ResponseEntity.ok(message);
    }

    // ✅ Add a new message
    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        Message savedMessage = messageService.addMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }

    // ✅ Update an existing message
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        Message updatedMessage = messageService.updateMessage(id, message);
        return ResponseEntity.ok(updatedMessage);
    }

    // ✅ Delete a message
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok("✅ Message deleted successfully!");
    }
}