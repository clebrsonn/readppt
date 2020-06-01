package br.com.hyteck.readppt.service;

import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XSLFSlideService {

    public void readXSLFSlide(List<XSLFSlide> slides) {
        slides.forEach(xslfShapes -> {


            readXSLFTheme(xslfShapes.getTheme());
            xslfShapes.getSlideLayout();
            xslfShapes.getBackground();
            xslfShapes.getCommentAuthorsPart();
//            xslfShapes.get
        });

    }

    public void readXSLFTheme(XSLFTheme xslfTheme) {

        xslfTheme.getMajorFont();
        xslfTheme.getMinorFont();
        //color
//        xslfTheme.getCTColor()
        xslfTheme.getName();
        xslfTheme.getXmlObject();
        xslfTheme.getPackagePart();
        xslfTheme.getParent();
        xslfTheme.getRelationParts();
        xslfTheme.isCommitted();
        xslfTheme.getRelations();
    }
}
