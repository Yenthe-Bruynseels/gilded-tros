package com.gildedtros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateQualityUpdatesQualityTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"eenItem"})
    void updateQualityLowersQualityByOneForNormalItemsIfSellInisMoreThanZero(String name) {
        Item[] items = new Item[] {
                new Item(name, 1, 5)
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
    void updateQualityDoesNotLowerQualityBelowZeroForNormalItems(int sellIn, int quality) {
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

    @ParameterizedTest
    @ValueSource(strings = {"Duplicate Code", "Long Methods", "Ugly Variable Names"})
    void updateQualityLowerQualityByTwoForSmellyItemsWhenSellInDateHasNotPassed(String name) {
        Item[] items = new Item[] {
                new Item(name, 1, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(3, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Duplicate Code", "Long Methods", "Ugly Variable Names"})
    void updateQualityLowerQualityByFourForSmellyItemsWhenSellInDateHasPassed(String name) {
        Item[] items = new Item[] {
                new Item(name, 0, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(1, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @CsvSource({"1,0", "1,1", "0,0", "0,1", "0,2", "0,3", "-1,0", "-1,1", "-1,2", "-1,3"})
    void updateQualityDoesNotLowerQualityBelowZeroForSmellyItems(int sellIn, int quality) {
        Item[] items = new Item[] {
                new Item("Duplicate Code", sellIn, quality),
                new Item("Long Methods", sellIn, quality),
                new Item("Ugly Variable Names", sellIn, quality)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(0, gildedTros.items[0].quality);
        assertEquals(0, gildedTros.items[1].quality);
        assertEquals(0, gildedTros.items[2].quality);
    }

    @Test
    void combinationOfItemsUpdatesAllQualitiesCorrectly() {
        Item[] items = new Item[] {
                new Item("Backstage passes for Re:Factor", 11, 5),
                new Item("B-DAWG Keychain", 11, 5),
                new Item("Long Methods", 11, 5),
                new Item("Good Wine", 11, 5),
                new Item("eenItem", 11, 5),
                new Item(null, 0, 5),
                new Item("Duplicate Code", 0, 5),
                new Item("Backstage passes for HAXX", 10, 5),
                new Item("Ugly Variable Names", 11, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertAll(() -> assertEquals(6, gildedTros.items[0].quality),
                () -> assertEquals(5, gildedTros.items[1].quality),
                () -> assertEquals(3, gildedTros.items[2].quality),
                () -> assertEquals(6, gildedTros.items[3].quality),
                () -> assertEquals(4, gildedTros.items[4].quality),
                () -> assertEquals(3, gildedTros.items[5].quality),
                () -> assertEquals(1, gildedTros.items[6].quality),
                () -> assertEquals(7, gildedTros.items[7].quality),
                () -> assertEquals(3, gildedTros.items[8].quality));
    }
}