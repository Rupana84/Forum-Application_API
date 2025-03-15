package com.example.forum.service;

import com.example.forum.model.Channel;
import com.example.forum.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    //  Get all channels
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    //  Find a channel by ID, return 404 if not found
    public Channel findChannelById(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));
    }

    //  Create a new channel
    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    //  Delete a channel, return 404 if not found
    public void deleteChannel(Long id) {
        if (!channelRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found");
        }
        channelRepository.deleteById(id);
    }

    //  Delete all channels
    public void deleteAllChannels() {
        channelRepository.deleteAll();
    }
}