package com.sailing.qstaccess.domain.one;

/**
 * @author: wangxy
 */

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t4_aps_camera")
@Entity
@Data
public class ApsCamera {
    @Id
    private Long id;
    private String name;
    private String longitude;
    private String latitude;
    @Column(name = "device_id")
    private String deviceId;
}
