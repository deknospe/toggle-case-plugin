package io.github.deknospe.casetoolkit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaseConverterTest {

    @Test
    void togglesLatinAndCyrillicLetters() {
        assertEquals(
                "hELLO, мИР! 123",
                CaseConverter.convert("Hello, Мир! 123", CaseStyle.TOGGLE)
        );
    }

    @Test
    void usesLocaleIndependentUpperAndLowerCase() {
        assertEquals("STRASSE", CaseConverter.convert("Straße", CaseStyle.UPPER));
        assertEquals("i", CaseConverter.convert("I", CaseStyle.LOWER));
    }

    @Test
    void convertsMixedSeparatorsAndCamelCaseToSnakeCase() {
        assertEquals(
                "parse_http_response_value",
                CaseConverter.convert("parseHTTPResponse-value", CaseStyle.SNAKE)
        );
    }

    @Test
    void convertsAcronymsToTitleCase() {
        assertEquals(
                "Hello World Api Client",
                CaseConverter.convert("hello_world APIClient", CaseStyle.TITLE)
        );
    }

    @Test
    void convertsWordsToCamelAndPascalCase() {
        assertEquals("xmlHttpRequest", CaseConverter.convert("XML_http-request", CaseStyle.CAMEL));
        assertEquals("XmlHttpRequest", CaseConverter.convert("XML_http-request", CaseStyle.PASCAL));
    }

    @Test
    void convertsWordsToKebabCase() {
        assertEquals(
                "multiple-selection-support",
                CaseConverter.convert("MultipleSelection_support", CaseStyle.KEBAB)
        );
    }

    @Test
    void handlesEmptyAndPunctuationOnlyInput() {
        assertEquals("", CaseConverter.convert("", CaseStyle.CAMEL));
        assertEquals("", CaseConverter.convert("---", CaseStyle.SNAKE));
    }
}
