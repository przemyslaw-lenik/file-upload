package com.exercise.fileupload.file;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagInvalidCharsFinderTest {

    private final TagInvalidCharsFinder patternFinder = new TagInvalidCharsFinder();

    @Test
    void shouldFindPatternInAllInputs() {
        // given
        List<String> matchingInputs = List.of(
                " s",
                " +",
                "text-",
                "text ",
                "t e xt",
                "te+x"
        );

        // when
        List<String> actual = matchingInputs
                .filter(patternFinder::find);

        // then
        assertThat(actual)
                .hasSize(matchingInputs.size());
    }

    @Test
    void shouldNotFindPatternInAnyInputs() {
        // given
        List<String> matchingInputs = List.of(
                "teXT",
                "string3576",
                "text",
                "tt&t",
                "t*()ext",
                "TEXT"
        );

        // when
        List<String> actual = matchingInputs
                .filter(patternFinder::find);

        // then
        assertThat(actual)
                .isEmpty();
    }
}