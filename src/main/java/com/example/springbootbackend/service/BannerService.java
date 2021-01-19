package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Banner;
import com.example.springbootbackend.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BannerService {
    private BannerRepository bannerRepository;

    @Autowired
    public BannerService(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    public Optional<Banner> findById(int id) {
        Optional<Banner> bannerData = bannerRepository.findById(id);
        return bannerData;
    }

    public Banner save(Banner banner) {
        return bannerRepository.save(banner);
    }

    public void deleteById(int id) {
        bannerRepository.deleteById(id);
    }

    public List<Banner> findByDeletedNot(boolean deleted) {
        return bannerRepository.findByDeletedNot(deleted);
    }

    public Banner getBannerByNameCategory(String name) {
        List<Banner> bannerList = bannerRepository.getBannerByNameCategory(name);
        return maxBanner(bannerList);
    }

    public Banner maxBanner(List<Banner> banners) {
        return banners.stream().sorted(Comparator.comparing(Banner::getPrice)).collect(Collectors.toList()).get(0);
    }
}
