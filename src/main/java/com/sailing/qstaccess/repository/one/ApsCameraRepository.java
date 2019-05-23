package com.sailing.qstaccess.repository.one;

import com.sailing.qstaccess.domain.one.ApsCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



public interface ApsCameraRepository extends JpaRepository<ApsCamera,Long> {

    @Modifying
    @Query(value="truncate table t4_aps_camera", nativeQuery = true)
     void truncateTable();
}
