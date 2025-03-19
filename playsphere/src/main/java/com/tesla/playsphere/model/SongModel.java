package com.tesla.playsphere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongModel {

    private String song_id;
    private String song_name;
    private String prev_song_id;
    private String next_song_id;
    private String song_url;

}
