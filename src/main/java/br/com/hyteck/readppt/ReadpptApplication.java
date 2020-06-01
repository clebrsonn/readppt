package br.com.hyteck.readppt;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class ReadpptApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ReadpptApplication.class, args);
    }

}
