package com.tesla.playsphere.controller;

import com.tesla.playsphere.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tesla.playsphere.model.SongModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/firebase")
    public String testFirebaseConnection() {
        return songService.testConnection();
    }

    @GetMapping("/test")
    public ResponseEntity<String> testingSongController(){
        return new ResponseEntity<>("Song Controller is working", HttpStatus.FOUND);
    }

    // ✅ Add a song (Dynamic collection)
    @PostMapping("/{collectionName}/add")
    public ResponseEntity<String> addSong(
            @PathVariable String collectionName,
            @RequestBody SongModel song) {
        return ResponseEntity.ok(songService.addSong(collectionName, song));
    }

    // ✅ Get a song by ID (Dynamic collection)
    @GetMapping("/{collectionName}/{songId}")
    public ResponseEntity<?> getSongById(
            @PathVariable String collectionName,
            @PathVariable String songId) {
        SongModel song = songService.getSongById(collectionName, songId);
        return (song != null) ? ResponseEntity.ok(song) : ResponseEntity.notFound().build();
    }

    // ✅ Delete a song (Dynamic collection)
    @DeleteMapping("/{collectionName}/{songId}")
    public ResponseEntity<String> deleteSong(
            @PathVariable String collectionName,
            @PathVariable String songId) {
        return ResponseEntity.ok(songService.deleteSong(collectionName, songId));
    }

    // ✅ Update a song (Dynamic collection)
    @PutMapping("/{collectionName}/update")
    public ResponseEntity<String> updateSong(
            @PathVariable String collectionName,
            @RequestBody SongModel song) {
        return ResponseEntity.ok(songService.updateSong(collectionName, song));
    }

    // ✅ Get all songs from a collection (Dynamic collection)
    @GetMapping("/{collectionName}/all")
    public ResponseEntity<List<SongModel>> getAllSongs(@PathVariable String collectionName) {
        List<SongModel> songs = songService.getAllSongs(collectionName);
        return ResponseEntity.ok(songs);
    }

}

