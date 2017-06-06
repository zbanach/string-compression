package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by zbanach on 06.06.17.
 */
public class StringRunLengthEncoderTest {
    private StringRunLengthEncoder encoder;


    @Before
    public void setUp() {
        encoder = new StringRunLengthEncoder();
    }

    @Test(expected = NullPointerException.class)
    public void givenNullString_CompressShouldThrowAnException() {
        encoder.compress(null);
    }

    @Test
    public void givenEmptyString_CompressShouldAlsoReturnEmptyString() {
        assertThat(encoder.compress("")).isEqualTo("");
    }

    @Test
    public void givenStringOfOneOrTwoCharacters_CompressShouldAlwaysReturnTheSameString() {
        assertThat(encoder.compress("a")).isEqualTo("a");
        assertThat(encoder.compress("B")).isEqualTo("B");
        assertThat(encoder.compress("aB")).isEqualTo("aB");
        assertThat(encoder.compress("aa")).isEqualTo("aa");
    }

    @Test
    public void givenStringThatWouldBecomeShorterAfterRunLengthEncoding_CompressShouldReturnEncodedString() {
        assertThat(encoder.compress("aaa")).isEqualTo("a3");
        assertThat(encoder.compress("aabcccccaaa")).isEqualTo("a2b1c5a3");
        assertThat(encoder.compress("AaBBBcdEEEEEEefff")).isEqualTo("A1a1B3c1d1E6e1f3");
    }

    @Test
    public void givenStringThatWouldNotBecomeShorterAfterRunLengthEncoding_CompressShouldReturnOriginalString() {
        assertThat(encoder.compress("abcdefg")).isEqualTo("abcdefg");
        assertThat(encoder.compress("aaBBccDD")).isEqualTo("aaBBccDD");
    }
}
