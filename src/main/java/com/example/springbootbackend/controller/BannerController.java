package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Banner;
import com.example.springbootbackend.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BannerController {

    private BannerRepository bannerRepository;

    @Autowired
    public BannerController(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @GetMapping("/banners")
    public ResponseEntity<List<Banner>> getAllBanners() {
        try {
            List<Banner> banners = new ArrayList<>();
            banners.addAll(bannerRepository.findAll());
            if (banners.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(banners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/banners/{id}")
    public ResponseEntity<Banner> getNameById(@PathVariable("id") int id) {
        Optional<Banner> bannerData = bannerRepository.findById(id);
        if (bannerData.isPresent()) {
            return new ResponseEntity<>(bannerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/banners")
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) {
        try {
            Banner bannerTemp = bannerRepository
                    .save(new Banner(banner.getName(), banner.getPrice(), banner.getCategory(), banner.getContent(), false));
            return new ResponseEntity<>(bannerTemp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/banners/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable("id") int id, @RequestBody Banner banner) {
        Optional<Banner> bannerData = bannerRepository.findById(id);
        if (bannerData.isPresent()) {
            Banner bannerTemp = bannerData.get();
            bannerTemp.setName(banner.getName());
            bannerTemp.setPrice(banner.getPrice());
            bannerTemp.setCategory(banner.getCategory());
            bannerTemp.setContent(banner.getContent());
            bannerTemp.setDeleted(banner.isDeleted());
            return new ResponseEntity<>(bannerRepository.save(bannerTemp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/banners/{id}")
    public ResponseEntity<HttpStatus> deleteBanner(@PathVariable("id") int id) {
        try {
            bannerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/banners")
    public ResponseEntity<HttpStatus> deleteAllBanners() {
        try {
            bannerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }
    }

    @GetMapping("/banners/not_deleted")
    public ResponseEntity<List<Banner>> findByDeletedNot() {
        try {
            List<Banner> banners = bannerRepository.findByDeletedNot(true);
            if (banners.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(banners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
