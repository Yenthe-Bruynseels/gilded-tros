package com.gildedtros;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if ("B-DAWG Keychain".equals(item.name)) continue;

            if (!"Good Wine".equals(item.name) && !isBackstagePass(item)) {
                decrementQuality(item);
            } else {
                incrementQuality(item);
                if (isBackstagePass(item)) {
                    if (item.sellIn < 11) {
                        incrementQuality(item);
                    }

                    if (item.sellIn < 6) {
                        incrementQuality(item);
                    }
                }
            }

            updateSellIn(item);

            if (item.sellIn < 0) {
                if (!"Good Wine".equals(item.name)) {
                    if (!isBackstagePass(item)) {
                        decrementQuality(item);
                    } else {
                        item.quality = 0;
                    }
                } else {
                    incrementQuality(item);
                }
            }
        }
    }

    private void updateSellIn(Item item) {
        item.sellIn--;
    }

    private boolean isBackstagePass(Item item) {
        return "Backstage passes for Re:Factor".equals(item.name) || "Backstage passes for HAXX".equals(item.name);
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    private void decrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}