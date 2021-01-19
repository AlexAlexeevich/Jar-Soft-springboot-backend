package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Banner;
import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.service.BannerService;
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

    private BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/banners")
    public ResponseEntity<List<Banner>> getAllBanners() {
        try {
            List<Banner> banners = new ArrayList<>();
            banners.addAll(bannerService.findAll());
            if (banners.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(banners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/banners/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable("id") int id) {
        Optional<Banner> bannerData = bannerService.findById(id);
        if (bannerData.isPresent()) {
            return new ResponseEntity<>(bannerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/banners")
    public ResponseEntity<Banner> createBanner(@RequestBody Banner banner) {
        try {
            Banner bannerTemp = bannerService
                    .save(new Banner(banner.getName(), banner.getPrice(), banner.getCategory(), banner.getContent(), false));
            return new ResponseEntity<>(bannerTemp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/banners/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable("id") int id, @RequestBody Banner banner) {
        Optional<Banner> bannerData = bannerService.findById(id);
        if (bannerData.isPresent()) {
            Banner bannerTemp = bannerData.get();
            bannerTemp.setName(banner.getName());
            bannerTemp.setPrice(banner.getPrice());
            bannerTemp.setCategory(banner.getCategory());
            bannerTemp.setContent(banner.getContent());
            bannerTemp.setDeleted(banner.isDeleted());
            return new ResponseEntity<>(bannerService.save(bannerTemp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/banners/{id}")
    public ResponseEntity<HttpStatus> deleteBanner(@PathVariable("id") int id) {
        try {
            bannerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/banners/not_deleted")
    public ResponseEntity<List<Banner>> findByDeletedNot() {
        try {
            List<Banner> banners = bannerService.findByDeletedNot(true);
            if (banners.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(banners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/banners/bid")
    public ResponseEntity<String> getBid(@RequestBody Category category) {
        try {
            String reqName = category.getReqName();
            return new ResponseEntity<>(bannerService.getBannerByNameCategory(reqName).getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
