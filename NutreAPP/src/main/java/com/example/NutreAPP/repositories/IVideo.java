package com.example.NutreAPP.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NutreAPP.models.Video;

@Repository
public interface IVideo extends JpaRepository<Video, UUID> {

    boolean existsByUrl(String url);

    
}