package com.tesla.playsphere.controller;

import com.tesla.playsphere.model.SongModel;
import com.tesla.playsphere.service.CloudinaryService;
import com.tesla.playsphere.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/api/upload")
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private SongService songService;

    @PostMapping("/mp3")
    public ResponseEntity<String> uploadMp3(@RequestParam("file") MultipartFile file,
                                            @RequestParam("song_name") String songName,
                                            @RequestParam("collectionName") String collectionName) {
        try {
            // ✅ Upload file to Cloudinary
            String songUrl = cloudinaryService.uploadFile(file);

            // ✅ Generate a unique song ID (You can use UUID.randomUUID().toString() if needed)
            String songId = UUID.randomUUID().toString();

            // ✅ Create a SongModel object
            SongModel song = new SongModel(songId, songName, null, null, songUrl);

            // ✅ Store metadata in Firestore
            String response = songService.addSong(collectionName, song);

            return ResponseEntity.ok("✅ Song Uploaded & Stored in Firestore!\n" + response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error: " + e.getMessage());
        }
    }
}

