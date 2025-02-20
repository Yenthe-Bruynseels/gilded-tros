package com.gildedtros.strategy;

import com.gildedtros.Item;

public class SmellyItemUpdateQualityStrategy implements ItemUpdateQualityStrategy {
    @Override
    public void updateQuality(Item item) {
        item.sellIn--;

        decrementQuality(item);
        if (item.sellIn < 0) {
            decrementQuality(item);
        }
    }

    private void decrementQuality(Item item) {
        if (item.quality < 2) {
            item.quality = 0;
        } else {
            item.quality -= 2;
        }
    }
}