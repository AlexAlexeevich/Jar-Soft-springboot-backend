package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
    List<Banner> findByDeletedNot(boolean deleted);
    List<Banner> findByName(String name);

}
