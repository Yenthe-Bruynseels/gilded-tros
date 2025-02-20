package com.gildedtros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateQualityUpdatesSellInTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "Backstage passes for Re:Factor",
            "Long Methods",
            "Good Wine",
            "eenItem",
            "Duplicate Code",
            "Backstage passes for HAXX",
            "Ugly Variable Names"})
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
                new Item("B-DAWG Keychain", 2, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(2, gildedTros.items[0].sellIn);
    }

    @Test
    void combinationOfItemsUpdatesAllQualitiesCorrectly() {
        Item[] items = new Item[] {
                new Item("Backstage passes for Re:Factor", 11, 5),
                new Item("B-DAWG Keychain", 11, 5),
                new Item("Long Methods", 11, 5),
                new Item("Good Wine", 11, 5),
                new Item("eenItem", 11, 5),
                new Item(null, 11, 5),
                new Item("Duplicate Code", 11, 5),
                new Item("Backstage passes for HAXX", 10, 5),
                new Item("Ugly Variable Names", 11, 5)
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