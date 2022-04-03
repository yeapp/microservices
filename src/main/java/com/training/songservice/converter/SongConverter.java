package com.training.songservice.converter;

import com.training.songservice.dto.SongDto;
import com.training.songservice.entity.Song;
import org.springframework.stereotype.Component;

@Component
public class SongConverter {

    public SongDto convertToDto(Song entity) {
        return new SongDto(entity.getId(), entity.getName(), entity.getArtist(), entity.getAlbum(), entity.getLength(),
                entity.getResourceId(), entity.getYear());
    }

    public Song convertToEntity(SongDto dto) {
        return new Song(dto.getId(), dto.getName(), dto.getArtist(), dto.getAlbum(), dto.getLength(),
                dto.getResourceId(), dto.getYear());
    }

}
