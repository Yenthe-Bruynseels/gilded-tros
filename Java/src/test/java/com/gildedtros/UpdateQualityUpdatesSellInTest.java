package com.gildedtros;

import org.junit.jupiter.api.Test;

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
}
