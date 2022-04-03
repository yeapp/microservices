package com.training.songservice.controller;


import com.training.songservice.dto.SongDto;
import com.training.songservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService service;

    @GetMapping("/{id}")
    public ResponseEntity<SongDto> getById(@PathVariable("id") Long id) {
        ResponseEntity<SongDto> response = null;
        try {
            SongDto song = service.get(id);
            ResponseEntity<SongDto> response = new ResponseEntity<>(song, HttpStatus.OK);
        } catch (RuntimeException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<SongDto> create(@RequestBody SongDto song) {
        try {
            SongDto createdSong = service.create(song);
            return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestParam(name = "id", required = false) Long[] ids) {
        service.deleteByIds(Arrays.asList(ids));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
