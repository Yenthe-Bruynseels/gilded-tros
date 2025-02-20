package com.gildedtros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
                new Item(B_DAWG_KEYCHAIN, 2, 80)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(80, gildedTros.items[0].quality);
    }

    @Test
    void updateQualityIncreasesQualityOfGoodWineByOneIfSellInDateHasNotPassed() {
        Item[] items = new Item[] {
                new Item(GOOD_WINE, 1, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(6, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void updateQualityIncreasesQualityOfGoodWineByTwoIfSellInDateHasPassed(int sellIn) {
        Item[] items = new Item[] {
                new Item(GOOD_WINE, sellIn, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(7, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @CsvSource({"1,50", "0,50", "0,49", "-1,50", "-1,49"})
    void updateQualityDoesNotIncreaseQualityOfGoodWineAboveFifty(int sellIn, int quality) {
        Item[] items = new Item[] {
                new Item(GOOD_WINE, sellIn, quality)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(50, gildedTros.items[0].quality);
    }

    @Test
    void updateQualityIncreasesQualityByOneForBackstagePassesWhenSellInDateIsMoreThanTen() {
        Item[] items = new Item[] {
                new Item(BACKSTAGE_PASS_FOR_RE_FACTOR, 11, 5),
                new Item(BACKSTAGE_PASS_FOR_HAXX, 11, 5)
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
                new Item(BACKSTAGE_PASS_FOR_RE_FACTOR, sellIn, 5),
                new Item(BACKSTAGE_PASS_FOR_HAXX, sellIn, 5)
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
                new Item(BACKSTAGE_PASS_FOR_RE_FACTOR, sellIn, 5),
                new Item(BACKSTAGE_PASS_FOR_HAXX, sellIn, 5)
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
                new Item(BACKSTAGE_PASS_FOR_RE_FACTOR, sellIn, 5),
                new Item(BACKSTAGE_PASS_FOR_HAXX, sellIn, 5)
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
                new Item(BACKSTAGE_PASS_FOR_RE_FACTOR, sellIn, quality),
                new Item(BACKSTAGE_PASS_FOR_HAXX, sellIn, quality)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(50, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @ValueSource(strings = {DUPLICATE_CODE, LONG_METHODS, UGLY_VARIABLE_NAMES})
    void updateQualityLowerQualityByTwoForSmellyItemsWhenSellInDateHasNotPassed(String name) {
        Item[] items = new Item[] {
                new Item(name, 1, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertEquals(3, gildedTros.items[0].quality);
    }

    @ParameterizedTest
    @ValueSource(strings = {DUPLICATE_CODE, LONG_METHODS, UGLY_VARIABLE_NAMES})
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
                new Item(DUPLICATE_CODE, sellIn, quality),
                new Item(LONG_METHODS, sellIn, quality),
                new Item(UGLY_VARIABLE_NAMES, sellIn, quality)
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
                new Item(BACKSTAGE_PASS_FOR_RE_FACTOR, 11, 5),
                new Item(B_DAWG_KEYCHAIN, 11, 80),
                new Item(LONG_METHODS, 11, 5),
                new Item(GOOD_WINE, 11, 5),
                new Item("eenItem", 11, 5),
                new Item(null, 0, 5),
                new Item(DUPLICATE_CODE, 0, 5),
                new Item(BACKSTAGE_PASS_FOR_HAXX, 10, 5),
                new Item(UGLY_VARIABLE_NAMES, 11, 5)
        };

        GildedTros gildedTros = new GildedTros(items);

        gildedTros.updateQuality();

        assertAll(() -> assertEquals(6, gildedTros.items[0].quality),
                () -> assertEquals(80, gildedTros.items[1].quality),
                () -> assertEquals(3, gildedTros.items[2].quality),
                () -> assertEquals(6, gildedTros.items[3].quality),
                () -> assertEquals(4, gildedTros.items[4].quality),
                () -> assertEquals(3, gildedTros.items[5].quality),
                () -> assertEquals(1, gildedTros.items[6].quality),
                () -> assertEquals(7, gildedTros.items[7].quality),
                () -> assertEquals(3, gildedTros.items[8].quality));
    }
}