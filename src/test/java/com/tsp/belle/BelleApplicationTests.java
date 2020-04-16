package com.tsp.belle;


import com.tsp.belle.file.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class BelleApplicationTests {


    @Autowired
    private FileService fileService;

    @Test
    void upload() throws FileNotFoundException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\dio.jpg");
        InputStream stream = new FileInputStream(file);
        String path = fileService.upload(stream, "jpg");
        System.out.println(path);
    }


    @Test
    public void test01() throws Exception {

    }
    @Test
    public void test1() throws Exception {}



    /*@Test
    void download(){
        String path = "M00/00/00/rB8Ht151gtOAc-twAAEcrQx4y0E881.jpg";
        fileService.download(path);
    }*/


}
