package com.example;

/**
 * This class represents an encoder that aims to compress strings using run-length encoding.
 */
public class StringRunLengthEncoder {

    /**
     * Compresses the given string (if possible) using run-length encoding. If encoding the string would not lead to
     * compression, the original string is returned.
     *
     * @param originalString a string to compress
     * @return a string encoded using RLE or the original string if RL-encoded string would not be shorter than the
     *         original one
     */
    public String compress(String originalString) {
        assertNotNull(originalString);

        if (originalString.length() <= 2)
            return originalString; // RL-encoded string of 0, 1 or 2 characters will never be shorter than the original

        String encodedString = encode(originalString);
        return encodedString.length() < originalString.length() ? encodedString : originalString;
    }

    private void assertNotNull(String originalString) {
        if (originalString == null)
            throw new NullPointerException("String to compress must not be null");
    }

    private String encode(String str) {
        StringBuilder encodedString = new StringBuilder();
        char currentSequenceCharacter = str.charAt(0);
        int currentSequenceLength = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == currentSequenceCharacter) {
                currentSequenceLength++;
            } else {
                encodedString.append(currentSequenceCharacter).append(currentSequenceLength);
                currentSequenceCharacter = str.charAt(i);
                currentSequenceLength = 1;
            }
        }
        encodedString.append(currentSequenceCharacter).append(currentSequenceLength);
        return encodedString.toString();
    }
}
