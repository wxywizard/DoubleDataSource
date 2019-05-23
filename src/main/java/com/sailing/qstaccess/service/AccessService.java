package com.sailing.qstaccess.service;

import com.sailing.qstaccess.domain.one.ApsCamera;
import com.sailing.qstaccess.domain.two.Camera;
import com.sailing.qstaccess.repository.one.ApsCameraRepository;
import com.sailing.qstaccess.repository.two.CameraRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author: wangxy
 */
@Service
@Slf4j
public class AccessService {

    @Autowired
    private ApsCameraRepository apsCameraRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Transactional
    public void mysqlCameraToPgApsCamera(){
        //先查询mysqlCamera表中数据
        List<Camera> cameraList = cameraRepository.findAll();
        //清空pg中ApsCamera数据
        apsCameraRepository.truncateTable();
        //将mysql数据灌入pg中
        for (Camera camera : cameraList) {
            ApsCamera apsCamera = new ApsCamera();
            String cameraName = camera.getName().trim();
            BeanUtils.copyProperties(camera,apsCamera);
            if (cameraName.contains("|")){
                String[] arrayStr = cameraName.split("\\|");
                apsCamera.setName(arrayStr[0]);
                apsCamera.setDeviceId(arrayStr[1]);
            }
            apsCameraRepository.save(apsCamera);
        }
    }

    public void test(){
        log.info("【定时任务。。。。】");
    }

}
