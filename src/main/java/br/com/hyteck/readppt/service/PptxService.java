package br.com.hyteck.readppt.service;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PptxService {


    public void lerPPT(InputStream inputstream) throws IOException {

//        //opening an existing slide show
//        File file = new File("C:\\Users\\clebr\\OneDrive\\testes.pptx");
//        FileInputStream inputstream = new FileInputStream(file);
//        XMLSlideShow ppt = new XMLSlideShow(inputstream);

        XMLSlideShow ppt = new XMLSlideShow(inputstream);

        //adding slides to the slodeshow
//        XSLFSlide slide1 = ppt.createSlide();
//        XSLFSlide slide2 = ppt.createSlide();

        final var slides = ppt.getSlides();


        System.out.println("getAllEmbeddedParts = " + ppt.getAllEmbeddedParts());

        ppt.getAllEmbeddedParts();
        System.out.println("getRelations = " + ppt.getRelations());

        ppt.getRelations();

        System.out.println("getRelationParts = " + ppt.getRelationParts());
        ppt.getRelationParts();


        slides.forEach(xslfShapes -> {

            System.out.println("theme = " + xslfShapes.getTheme());


            xslfShapes.getSlideLayout().forEach(xslfShape -> {
                System.out.println("xslfShape = " + xslfShape);
                System.out.println("getPlaceholder = " + xslfShape.getPlaceholder());

            });


        });

        //saving the changes
//        FileOutputStream out = new FileOutputStream(file);
//        ppt.write(out);

        System.out.println("Presentation edited successfully");
//        out.close();


    }

    void readSlides(XMLSlideShow ppt) {
        final var slides = ppt.getSlides();

        System.out.println("ppt.getSlides() = " + slides);

        slides.forEach(xslfShapes -> {

        });

    }

    void readSlidesMasters(XMLSlideShow ppt) {
        final var masters = ppt.getSlideMasters();
        System.out.println("getPictureData = " + ppt.getPictureData());


    }

    void readCtPresentation(XMLSlideShow ppt) {
        System.out.println("getCTPresentation = " + ppt.getCTPresentation());

        final var ctPresentation = ppt.getCTPresentation();
    }
//    void readPictureData(XMLSlideShow ppt) {
//        final var pictureData = ppt.getPictureData();
//    }
//    void readPictureData(XMLSlideShow ppt) {
//        final var pictureData = ppt.getPictureData();
//    }
//    void readPictureData(XMLSlideShow ppt) {
//        final var pictureData = ppt.getPictureData();
//    }


}
