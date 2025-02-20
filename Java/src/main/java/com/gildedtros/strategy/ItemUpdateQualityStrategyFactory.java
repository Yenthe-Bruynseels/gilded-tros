package com.gildedtros.strategy;

import com.gildedtros.Item;

public class ItemUpdateQualityStrategyFactory {

    public static ItemUpdateQualityStrategy getStrategy(Item item) {
        return switch (item.name) {
            case "B-DAWG Keychain" -> new LegendaryItemUpdateQualityStrategy();
            case "Good Wine" -> new GoodWineUpdateQualityStrategy();
            case "Backstage passes for Re:Factor", "Backstage passes for HAXX" -> new BackstagePassUpdateQualityStrategy();
            case "Duplicate Code", "Long Methods", "Ugly Variable Names" -> new SmellyItemUpdateQualityStrategy();
            case null, default -> new NormalItemUpdateQualityStrategy();
        };
    }
}