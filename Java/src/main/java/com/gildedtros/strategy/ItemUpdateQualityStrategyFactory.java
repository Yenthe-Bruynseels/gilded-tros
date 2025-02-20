package com.gildedtros.strategy;

import com.gildedtros.Item;

import static com.gildedtros.ItemNameConstants.BACKSTAGE_PASS_FOR_HAXX;
import static com.gildedtros.ItemNameConstants.BACKSTAGE_PASS_FOR_RE_FACTOR;
import static com.gildedtros.ItemNameConstants.B_DAWG_KEYCHAIN;
import static com.gildedtros.ItemNameConstants.DUPLICATE_CODE;
import static com.gildedtros.ItemNameConstants.GOOD_WINE;
import static com.gildedtros.ItemNameConstants.LONG_METHODS;
import static com.gildedtros.ItemNameConstants.UGLY_VARIABLE_NAMES;

public class ItemUpdateQualityStrategyFactory {

    public static ItemUpdateQualityStrategy getStrategy(Item item) {
        return switch (item.name) {
            case B_DAWG_KEYCHAIN -> new LegendaryItemUpdateQualityStrategy();
            case GOOD_WINE -> new GoodWineUpdateQualityStrategy();
            case BACKSTAGE_PASS_FOR_RE_FACTOR, BACKSTAGE_PASS_FOR_HAXX -> new BackstagePassUpdateQualityStrategy();
            case DUPLICATE_CODE, LONG_METHODS, UGLY_VARIABLE_NAMES -> new SmellyItemUpdateQualityStrategy();
            case null, default -> new NormalItemUpdateQualityStrategy();
        };
    }
}