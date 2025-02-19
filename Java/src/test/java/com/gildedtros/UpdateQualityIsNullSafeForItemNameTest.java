package com.gildedtros;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UpdateQualityIsNullSafeForItemNameTest {

    @Test
    void updateQualityDoesNotThrowNullPointerExceptionWhenNameIsNull() {
        Item[] items = new Item[] {
                new Item(null, 1, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        assertDoesNotThrow(gildedTros::updateQuality);
    }
}
