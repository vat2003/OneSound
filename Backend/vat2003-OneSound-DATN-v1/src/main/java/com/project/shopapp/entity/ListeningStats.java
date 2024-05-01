package com.project.shopapp.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListeningStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long listens;
    @Temporal(TemporalType.DATE)
    private Date dateLis;

    @ManyToOne
    @JoinColumn(name = "songId")
    private Song song;

    public ListeningStats(Long listens, Date dateLis, Song song) {
        this.listens = listens;
        this.dateLis = dateLis;
        this.song = song;
    }

}
