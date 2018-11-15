package com.lancet.iplancet.dao;

import com.lancet.iplancet.entity.MedicalRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jimmy
 * @date 2018/11/13
 * @Description:
 */
@Mapper
public interface MedicalRecordMapper {


    MedicalRecord getMrInfo(@Param("emrId") String emrId);
}
