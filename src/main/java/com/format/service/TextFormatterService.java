package com.format.service;

import com.format.exception.UnknownAlignmentException;
import com.format.util.TextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TextFormatterService {


    public String alignText(String text, String type, int length) {
        if (length <= 0) {
            throw new UnknownAlignmentException(String.format("invalid alignment length %d", length));
        } else if (!TextUtil.validAlignmentType(type)) {
            throw new UnknownAlignmentException(String.format("invalid alignment type %s", type));
        }

        String normalizedText = StringUtils.normalizeSpace(text);
        List<String> splitText = TextUtil.splitTextWithWordBreak(normalizedText, length);
        List<String> alignedTextList = new ArrayList<>();
        splitText.stream()
                .iterator()
                .forEachRemaining(part -> {
                    if ("LEFT".equalsIgnoreCase(type)) {
                        alignedTextList.add(StringUtils.rightPad(part, length));
                    } else if ("RIGHT".equalsIgnoreCase(type)) {
                        alignedTextList.add(StringUtils.leftPad(part, length));
                    } else if ("CENTER".equalsIgnoreCase(type)) {
                        alignedTextList.add(StringUtils.center(part, length));
                    }
                });
        return String.join("\n", alignedTextList);
    }


}
