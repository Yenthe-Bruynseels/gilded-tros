package com.gildedtros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateQualityUpdatesQualityTest {

    @Test
    void updateQualityLowersQualityByOneForNormalItemsIfSellInisMoreThanZero() {
        Item[] items = new Item[] {
                new Item("eenItem", 1, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(4, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void updateQualityLowersQualityByTwoForNormalItemsIfSellInisZeroOrLess(int sellIn) {
        Item[] items = new Item[] {
                new Item("eenItem", sellIn, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(3, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @CsvSource({"1,0","0,0","0,1","-1,0","-1,0"})
    void updateQualityDoesNotLowerQualityBelowZero(int sellIn, int quality) {
        Item[] items = new Item[] {
                new Item("eenItem", sellIn, quality)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(0, gildedTros.items[0].quality);
    }

    @Test
    void updateQualityDoesNotChangeQualityForLegendaryItems() {
        Item[] items = new Item[] {
                new Item("B-DAWG Keychain", 2, 80)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(80, gildedTros.items[0].quality);
    }

    @Test
    void updateQualityIncreasesQualityOfGoodWineByOneIfSellInDateHasNotPassed() {
        Item[] items = new Item[] {
                new Item("Good Wine", 1, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(6, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void updateQualityIncreasesQualityOfGoodWineByTwoIfSellInDateHasPassed(int sellIn) {
        Item[] items = new Item[] {
                new Item("Good Wine", sellIn, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(7, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @CsvSource({"1,50", "0,50", "0,49", "-1,50", "-1,49"})
    void updateQualityDoesNotIncreaseQualityOfGoodWineAboveFifty(int sellIn, int quality) {
        Item[] items = new Item[] {
                new Item("Good Wine", sellIn, quality)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(50, gildedTros.items[0].quality);
    }

    @Test
    void updateQualityIncreasesQualityByOneForBackstagePassesWhenSellInDateIsMoreThanTen() {
        Item[] items = new Item[] {
                new Item("Backstage passes for Re:Factor", 11, 5),
                new Item("Backstage passes for HAXX", 11, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(6, gildedTros.items[0].quality);
        assertEquals(6, gildedTros.items[1].quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {10,6})
    void updateQualityIncreasesQualityByTwoForBackstagePassesWhenSellInDateIsTenOrLowerButHigherThanFive(int sellIn) {
        Item[] items = new Item[] {
                new Item("Backstage passes for Re:Factor", sellIn, 5),
                new Item("Backstage passes for HAXX", sellIn, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(7, gildedTros.items[0].quality);
        assertEquals(7, gildedTros.items[1].quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {5,1})
    void updateQualityIncreasesQualityByThreeForBackstagePassesWhenSellInDateIsFiveOrLowerButHigherThanZero(int sellIn) {
        Item[] items = new Item[] {
                new Item("Backstage passes for Re:Factor", sellIn, 5),
                new Item("Backstage passes for HAXX", sellIn, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(8, gildedTros.items[0].quality);
        assertEquals(8, gildedTros.items[1].quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    void updateQualitySetsQualityForBackstagePassesToZeroWhenSellInDateIsZeroOrLower(int sellIn) {
        Item[] items = new Item[] {
                new Item("Backstage passes for Re:Factor", sellIn, 5),
                new Item("Backstage passes for HAXX", sellIn, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(0, gildedTros.items[0].quality);
        assertEquals(0, gildedTros.items[1].quality);
    }

    @ParameterizedTest
    @CsvSource({"11,50", "10,50", "10,49", "10,48", "5,50", "5,49", "5,48", "5,47"})
    void updateQualityDoesNotIncreaseQualityOfBackstagePassesAboveFifty(int sellIn, int quality) {
        Item[] items = new Item[] {
                new Item("Backstage passes for Re:Factor", sellIn, quality),
                new Item("Backstage passes for HAXX", sellIn, quality)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(50, gildedTros.items[0].quality);
    }
}