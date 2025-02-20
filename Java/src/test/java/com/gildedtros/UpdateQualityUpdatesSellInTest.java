package com.gildedtros;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateQualityUpdatesSellInTest {

    @Test
    void updateQualityLowersSellIn() {
        Item[] items = new Item[] {
                new Item("eenItem", 2, 5)
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
                new Item("Backstage passes for HAXX", 10, 5),
                new Item("B-DAWG Keychain", 11, 5),
                new Item("Good Wine", 11, 5),
                new Item("eenItem", 11, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertAll(() -> assertEquals(10, gildedTros.items[0].sellIn),
                () -> assertEquals(9, gildedTros.items[1].sellIn),
                () -> assertEquals(11, gildedTros.items[2].sellIn),
                () -> assertEquals(10, gildedTros.items[3].sellIn),
                () -> assertEquals(10, gildedTros.items[4].sellIn));
    }
}
