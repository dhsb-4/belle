package com.tsp.belle;


<<<<<<< HEAD
import com.google.common.collect.Lists;
import com.tsp.belle.entity.User;
import com.tsp.belle.service.RedisService;
=======
>>>>>>> 71e1094152aa3c2fd6b6e19129d263d46e6db314
import com.tsp.belle.file.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    @Test
    public void testConsumer(){
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        Demo<Integer> demo = new Demo<>();
        demo.setList(list);
        demo.consumer(System.out::println);

    }


}

class Demo<T>{
    private List<T> list = new ArrayList();
    public void setList(List<T> list){
        this.list = list;
    }

    public void consumer(Consumer<? super T> consumer){
        for (T  value:list)
        consumer.accept(value);
    }
}