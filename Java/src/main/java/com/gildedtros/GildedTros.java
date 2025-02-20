package com.gildedtros;

import com.gildedtros.strategy.ItemUpdateQualityStrategy;
import com.gildedtros.strategy.ItemUpdateQualityStrategyFactory;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdateQualityStrategy strategy = ItemUpdateQualityStrategyFactory.getStrategy(item);
            strategy.updateQuality(item);
        }
    }
}