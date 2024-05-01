package com.project.shopapp.entity;

import java.util.Date;

import com.google.api.client.util.DateTime;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoryListen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "songId")
    private Song song;

    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date listenTime;

    public HistoryListen(Song song, Long userId, Date listenTime) {
        this.song = song;
        this.userId = userId;
        this.listenTime = listenTime;
    }

}
