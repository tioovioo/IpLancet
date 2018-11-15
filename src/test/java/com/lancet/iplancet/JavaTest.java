package com.lancet.iplancet;

import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.io.File;

/**
 * @author Jimmy
 * @date 2018/11/14
 * @Description:
 */
public class JavaTest {



    @Test
    public void test01(){

        System.out.println(System.getProperty("user.dir"));
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
    }


    @Test
    public void file1(){
        String path = "D:/file/as/d/a.txt";
        File file = new File(path);
        if(!file.exists())
        {
            file.mkdirs();
        }
        System.out.println(file.getName());
    }

    @Test
    public void file2() throws  Exception
    {
        File file = new File("D:/file/as/d/a.txt");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }


}
