package com.lancet.iplancet.controller;

import com.lancet.iplancet.dao.MedicalRecordMapper;
import com.lancet.iplancet.dto.Result;
import com.lancet.iplancet.entity.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jimmy
 * @date 2018/11/13
 * @Description:
 */

@SuppressWarnings("all")
@RestController
public class Login {

    @Autowired
    public MedicalRecordMapper medicalRecordMapper;

    @GetMapping("/login")
    public String doLogin(){

        String str = "现在是北京时间: ";
        str += new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());

        return str;
    }

    /**
     * 根据emrId获取病历信息.
     * @param emrId
     * @return
     */
    @GetMapping("/mr/Info")
    public Result getMrInfo(String emrId){
        try {
            MedicalRecord mr = medicalRecordMapper.getMrInfo(emrId);
            return new Result(1,mr);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new Result(0,"获取失败.");
    }

    @RequestMapping("doLogin")
    public Result doLogin(HttpSession session){


        return  new Result(1,"当前sessionId为:" + session.getId());
    }




























}
