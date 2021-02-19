package com.format.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@UtilityClass
public class TextUtil {

    private static final String REGEX_SPLIT_BY_LENGTH = "(?<=\\G.{%d})";

    /**
     * will break text into parts by word by word, however if word size is more than provided max length then will break words
     * @param text sentences to align
     * @param maxLength max length of a line
     * @return list words per line
     */
    public List<String> splitTextWithWordBreak(String text, int maxLength) {
        String[] textArray = text.split(" ");
        List<String> result = new ArrayList<>();
        result.add("");
        Arrays.stream(textArray).iterator().forEachRemaining(part -> {
            if (part.length() >= maxLength) {
                Arrays.stream(part.split(String.format(REGEX_SPLIT_BY_LENGTH,maxLength)))
                        .iterator()
                        .forEachRemaining(result::add);
            } else if (maxLength > (result.get(result.size() - 1).length() + part.length() + 1)) {
                StringBuilder stringBuilder = new StringBuilder(result.get(result.size() - 1));
                // check if last element is empty no need to add space
                if (!stringBuilder.toString().isEmpty()) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(part);
                result.remove(result.size() - 1);
                result.add(stringBuilder.toString());
            } else {
                result.add(part);
            }
        });

        return result;
    }


    public boolean validAlignmentType(String type) {
        return "LEFT".equalsIgnoreCase(type)
                || "RIGHT".equalsIgnoreCase(type)
                || "CENTER".equalsIgnoreCase(type);
    }
}
