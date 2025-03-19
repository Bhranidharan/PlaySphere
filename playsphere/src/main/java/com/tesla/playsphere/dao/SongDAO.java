package com.tesla.playsphere.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
        import com.google.firebase.cloud.FirestoreClient;
import com.tesla.playsphere.model.SongModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class SongDAO {

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    // ✅ Add a new song to a specific collection
    public String addSong(String collectionName, SongModel song) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> result = db.collection(collectionName)
                .document(song.getSong_id())
                .set(song);
        return "Song added in '" + collectionName + "' at: " + result.get().getUpdateTime();
    }

    // ✅ Get a song by ID from a specific collection
    public SongModel getSongById(String collectionName, String songId) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(songId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(SongModel.class);
        } else {
            return null; // Song not found
        }
    }

    // ✅ Delete a song by ID from a specific collection
    public String deleteSong(String collectionName, String songId) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> result = db.collection(collectionName).document(songId).delete();
        return "Song deleted from '" + collectionName + "' at: " + result.get().getUpdateTime();
    }

    // ✅ Update song details in a specific collection
    public String updateSong(String collectionName, SongModel song) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> result = db.collection(collectionName)
                .document(song.getSong_id())
                .set(song);
        return "Song updated in '" + collectionName + "' at: " + result.get().getUpdateTime();
    }

    public List<SongModel> getAllSongs(String collectionName) {
        List<SongModel> songs = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();

        try {
            CollectionReference collection = db.collection(collectionName);
            ApiFuture<QuerySnapshot> future = collection.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                SongModel song = document.toObject(SongModel.class);
                songs.add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

}
