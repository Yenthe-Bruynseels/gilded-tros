package com.gildedtros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.gildedtros.ItemNameConstants.BACKSTAGE_PASS_FOR_HAXX;
import static com.gildedtros.ItemNameConstants.BACKSTAGE_PASS_FOR_RE_FACTOR;
import static com.gildedtros.ItemNameConstants.B_DAWG_KEYCHAIN;
import static com.gildedtros.ItemNameConstants.DUPLICATE_CODE;
import static com.gildedtros.ItemNameConstants.GOOD_WINE;
import static com.gildedtros.ItemNameConstants.LONG_METHODS;
import static com.gildedtros.ItemNameConstants.UGLY_VARIABLE_NAMES;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateQualityUpdatesSellInTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            BACKSTAGE_PASS_FOR_RE_FACTOR,
            LONG_METHODS,
            GOOD_WINE,
            "eenItem",
            DUPLICATE_CODE,
            BACKSTAGE_PASS_FOR_HAXX})
    void updateQualityLowersSellIn(String name) {
        Item[] items = new Item[] {
                new Item(name, 2, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(1, gildedTros.items[0].sellIn);
    }

    @Test
    void updateQualityDoesNotLowerSellInForLegendaryItems() {
        Item[] items = new Item[] {
                new Item(B_DAWG_KEYCHAIN, 2,80)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(2, gildedTros.items[0].sellIn);
    }

    @Test
    void combinationOfItemsUpdatesAllQualitiesCorrectly() {
        Item[] items = new Item[] {
                new Item(BACKSTAGE_PASS_FOR_RE_FACTOR, 11, 5),
                new Item(B_DAWG_KEYCHAIN, 11,80),
                new Item(LONG_METHODS, 11, 5),
                new Item(GOOD_WINE, 11, 5),
                new Item("eenItem", 11, 5),
                new Item(null, 11, 5),
                new Item(DUPLICATE_CODE, 11, 5),
                new Item(BACKSTAGE_PASS_FOR_HAXX, 10, 5),
                new Item(UGLY_VARIABLE_NAMES, 11, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertAll(() -> assertEquals(10, gildedTros.items[0].sellIn),
                () -> assertEquals(11, gildedTros.items[1].sellIn),
                () -> assertEquals(10, gildedTros.items[2].sellIn),
                () -> assertEquals(10, gildedTros.items[3].sellIn),
                () -> assertEquals(10, gildedTros.items[4].sellIn),
                () -> assertEquals(10, gildedTros.items[5].sellIn),
                () -> assertEquals(10, gildedTros.items[6].sellIn),
                () -> assertEquals(9, gildedTros.items[7].sellIn),
                () -> assertEquals(10, gildedTros.items[8].sellIn));
    }
}