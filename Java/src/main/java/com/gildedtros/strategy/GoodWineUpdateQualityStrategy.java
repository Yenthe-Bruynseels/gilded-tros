package com.gildedtros.strategy;

import com.gildedtros.Item;

public class GoodWineUpdateQualityStrategy implements ItemUpdateQualityStrategy {
    @Override
    public void updateQuality(Item item) {
        item.sellIn--;

        incrementQuality(item);

        if (item.sellIn < 0) {
            incrementQuality(item);
        }
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}
