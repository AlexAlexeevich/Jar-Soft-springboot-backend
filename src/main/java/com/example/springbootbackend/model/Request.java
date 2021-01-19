package com.example.springbootbackend.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "banner_id", referencedColumnName = "id")
    private Banner banner;

    private String userAgent;

    private String ipAddress;

    private LocalDateTime date;

    public Request() {
    }

    public Request(Banner banner, String userAgent, String ipAddress, LocalDateTime date) {
        this.banner = banner;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
