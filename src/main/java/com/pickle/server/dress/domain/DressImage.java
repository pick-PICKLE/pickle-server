package com.pickle.server.dress.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "dress_image")
public class DressImage {

    @Id
    @Column(name ="dress_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dress_id", nullable = false)
    private Dress dress;
}