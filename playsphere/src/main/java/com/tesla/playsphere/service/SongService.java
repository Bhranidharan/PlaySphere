package com.tesla.playsphere.service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.tesla.playsphere.dao.SongDAO;
import com.tesla.playsphere.model.SongModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongDAO songDAO;

    public String testConnection() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            return db != null ? "✅ Firebase Firestore Connected Successfully!" : "❌ Connection Failed!";
        } catch (Exception e) {
            return "❌ Error connecting to Firebase: " + e.getMessage();
        }
    }


    // ✅ Add a song
    public String addSong(String collectionName, SongModel song) {
        try {
            return songDAO.addSong(collectionName, song);
        } catch (Exception e) {
            return "❌ Error adding song: " + e.getMessage();
        }
    }

    // ✅ Get a song by ID
    public SongModel getSongById(String collectionName, String songId) {
        try {
            return songDAO.getSongById(collectionName, songId);
        } catch (Exception e) {
            return null; // Handle error gracefully
        }
    }

    // ✅ Delete a song by ID
    public String deleteSong(String collectionName, String songId) {
        try {
            return songDAO.deleteSong(collectionName, songId);
        } catch (Exception e) {
            return "❌ Error deleting song: " + e.getMessage();
        }
    }

    // ✅ Update a song
    public String updateSong(String collectionName, SongModel song) {
        try {
            return songDAO.updateSong(collectionName, song);
        } catch (Exception e) {
            return "❌ Error updating song: " + e.getMessage();
        }
    }

    // get all song
    public List<SongModel> getAllSongs(String collectionName) {
        try {
            return songDAO.getAllSongs(collectionName);
        } catch (Exception e) {
            return new ArrayList<>(); // Return empty list if error occurs
        }
    }
}

