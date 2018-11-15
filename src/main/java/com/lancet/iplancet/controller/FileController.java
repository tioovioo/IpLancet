package com.lancet.iplancet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Jimmy
 * @date 2018/11/14
 * @Description:
 */

@Controller
public class FileController {


    /**
     * 跳转到指定页面.
     * @return
     */
    @RequestMapping("file")
    public String file(){
        return "/file";
    }

    /**
     * 跳转到指定页面.
     * @return
     */
    @RequestMapping("multifile")
    public String multiFile(){
        return "/multifile";
    }


    /**
     *  单个文件上传.
     * @param file
     * @return
     */
    @PostMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName")MultipartFile file){

        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "D:/file/upload111" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return fileName+"上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return fileName+"上传失败!!!";
        }
    }



    @RequestMapping("multifileUpload")
    @ResponseBody
    public String multifileUpload(@RequestParam("fileName") List<MultipartFile> files){
        if(files.isEmpty()){
            return  "文件为空";
        }

        String path = "D:/file/multiFile";
        for (MultipartFile file : files) {
            if(file.isEmpty()){
                return  "文件为空";
            }
            String name = file.getOriginalFilename();
            String suffix = name.substring(name.lastIndexOf(".") + 1);
            System.out.println("正在上传文件: "+ name +
                    " 文件size " +file.getSize()+ "文件的后缀 "+suffix);


            File dir = new File(path,System.currentTimeMillis()+name);
            if(!dir.getParentFile().exists()){
                dir.getParentFile().mkdirs();
            }

            try {
                file.transferTo(dir);
            }catch (Exception e){
                e.printStackTrace();
                return "失败";
            }
        }

        return "导入成功!!!!!!!!";
    }



    @RequestMapping("download")
    public void fileDownload(HttpServletResponse response) throws  Exception {
        String path = "D:/file/upload/入院记录.xml";

        byte[] buffer = new byte[1024];

        File file = new File(path);

        if(file.exists()){ //判断文件父目录是否存在
            //获取输入流
            InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
            String downLoadName = URLEncoder.encode("入院记录222.xml","UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName="+downLoadName);
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while((len = bis.read(buffer)) != -1){
                out.write(buffer);
                out.flush();
            }
            out.close();
        }
    }

    /**
     * 文件下载.
     * @param response
     */
    @RequestMapping(value = "demoForDownload",method = RequestMethod.GET)
    public void demo4Download(HttpServletResponse response) throws Exception{
        String sourcePath = "D:/file/upload/入院记录.xml";
        String downloadName = "下载文件.xml";

        File file = new File(sourcePath);
        //如果文件存在
        if(file.exists()){
            //输入和输出流
            byte[] buffer = new byte[1024];
            InputStream in = new BufferedInputStream(new FileInputStream(new File(sourcePath)));
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

            //设置下载相关配置:
            downloadName = URLEncoder.encode(downloadName,"UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName="+downloadName);
            response.setContentType("multipart/form-data");
            int i = 0;
            while((i = in.read(buffer))!= -1){
                out.write(buffer);
                //刷新
                out.flush();

            }

            //关闭资源:
            out.close();
            in.close();

        }






    }


}
