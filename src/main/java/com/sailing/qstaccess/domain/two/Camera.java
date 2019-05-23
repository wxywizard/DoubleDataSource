package com.sailing.qstaccess.domain.two;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: wangxy
 */
@Table(name = "camera")
@Entity
@Data
public class Camera {
    @Id
    private Long id;
    private String name;
    private String cameratype;
    private String type;
    private String region;
    private String longitude;
    private String latitude;
    private String direction;
    private String location;
    private String status;
    private String dsc;
    private String brandid;
    private String brandname;
    private String model;
    private String ip;
    private String port1;
    private String port2;
    private String account;
    private String password;
    private String channel;
    private String extcameraid;
    private String admindept;
    private String admin;
    private String telephone;
    private String address;
    private String url;
    private String follwarea;
    private String thumb_nail;
    private String cameragroupid;
    private String create_time;
    private String isHighSpeedIntersection;
}
