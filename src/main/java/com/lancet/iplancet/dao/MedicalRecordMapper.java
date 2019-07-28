package com.lancet.iplancet.dao;

import com.lancet.iplancet.entity.MedicalRecord;
import com.lancet.iplancet.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yongjia.guo
 * @date 2018/11/13
 * @Description:
 */
@Mapper
public interface MedicalRecordMapper {


    /**
     * select on record from t_medical_record.
     * @param emrId
     * @return
     */
    MedicalRecord getMrInfo(@Param("emrId") String emrId);

    /**
     * Insert Data Into t_user.(mysql)
     * @param user
     * @return
     */
    Integer insertToUser(@Param("user") User user);

    /**
     * Insert Data Into t_user.(oracle)
     * @param user
     * @return
     */
    Integer insertToUserForOracle(@Param("user") User user);
}
