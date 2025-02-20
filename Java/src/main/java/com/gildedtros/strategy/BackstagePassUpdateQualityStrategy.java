package com.gildedtros.strategy;

import com.gildedtros.Item;

public class BackstagePassUpdateQualityStrategy implements ItemUpdateQualityStrategy {
    @Override
    public void updateQuality(Item item) {
        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality = 0;
            return;
        }

        incrementQuality(item);

        if (item.sellIn < 10) {
            incrementQuality(item);
        }

        if (item.sellIn < 5) {
            incrementQuality(item);
        }
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}
