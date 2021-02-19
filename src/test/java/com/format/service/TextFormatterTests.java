package com.format.service;

import com.format.exception.UnknownAlignmentException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
public class TextFormatterTests {
    private static final String LEFT_ALIGNED = "This text should be left aligned ";
    private static final String RIGHT_ALIGNED = "This text should be right aligned ";
    private static final String CENTER_ALIGNED = "This text should be center aligned ";
    private static final String LENGTHY_TEXT = "Since there was very little education derived from comics, Honorificabilitudinitatibus teachers rarely used them as reading material due to this floccinaucinihilipilification.";
    private TextFormatterService service;

    @BeforeEach
    void setUp() {
        service = new TextFormatterService();
    }


    @Test
    void validateLeftAlign() {
        Assertions.assertEquals("This text \n" +
                        "should be \n" +
                        "left      \n" +
                        "aligned   ",
                service.alignText(LEFT_ALIGNED, "LEFT", 10),
                "Correctly left aligned");

        Assertions.assertEquals("This text should be \n" +
                        "left aligned        ",
                service.alignText(LEFT_ALIGNED, "LEFT", 20),
                "Correctly left aligned");
    }

    @Test
    void validateRightAlign() {
        Assertions.assertEquals(" This text\n" +
                        " should be\n" +
                        "     right\n" +
                        "   aligned", service.alignText(RIGHT_ALIGNED, "RIGHT", 10),
                "Correctly right aligned");

        Assertions.assertEquals(" This text should be\n" +
                        "       right aligned", service.alignText(RIGHT_ALIGNED, "RIGHT", 20),
                "Correctly right aligned");
    }

    @Test
    void validateCenterAlign() {
        Assertions.assertEquals("This text \n" +
                        "should be \n" +
                        "  center  \n" +
                        " aligned  ",
                service.alignText(CENTER_ALIGNED, "CENTER", 10),
                "Correctly center aligned");

        Assertions.assertEquals("This text should be \n" +
                        "   center aligned   ",
                service.alignText(CENTER_ALIGNED, "CENTER", 20),
                "Correctly center aligned");
    }

    @Test
    void validateLengthyText(){
        Assertions.assertEquals("Since there was     \n" +
                        "very little         \n" +
                        "education derived   \n" +
                        "from comics,        \n" +
                        "Honorificabilitudini\n" +
                        "tatibus teachers    \n" +
                        "rarely used them as \n" +
                        "reading material    \n" +
                        "due to this         \n" +
                        "floccinaucinihilipil\n" +
                        "ification.          ",
                service.alignText(LENGTHY_TEXT, "LEFT", 20),
                "Correctly center aligned");
    }

    @Test
    void validateExceptions() {
        // alignment type is valid
        Exception thrown = Assertions.assertThrows(UnknownAlignmentException.class, () -> service.alignText("", "UNKNOWN", 10));
        Assertions.assertEquals("invalid alignment type UNKNOWN", thrown.getMessage());

        // length is positive
        thrown = Assertions.assertThrows(UnknownAlignmentException.class, () -> service.alignText("", "LEFT", -10));
        Assertions.assertEquals("invalid alignment length -10", thrown.getMessage());
    }
}
