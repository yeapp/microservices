package com.training.songservice.service;

import com.training.songservice.converter.SongConverter;
import com.training.songservice.dto.SongDto;
import com.training.songservice.entity.Song;
import com.training.songservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository repository;

    @Autowired
    private SongConverter converter;

    public SongDto create(SongDto dto) {
        Song entity = repository.save(converter.convertToEntity(dto));
        return converter.convertToDto(entity);
    }

    public SongDto get(Long songId) {
        Optional<Song> songData = repository.findById(songId);
        if (songData.isPresent()) {
            return converter.convertToDto(songData.get());
        }
        throw new RuntimeException("Song by id =" + songId + " is not found.");
    }

    public void deleteByIds(List<Long> ids) {
        repository.deleteAllById(ids);
    }

}
