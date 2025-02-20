package com.gildedtros;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if ("B-DAWG Keychain".equals(item.name)) continue;

            if (!"Good Wine".equals(item.name)
                    && !isBackstagePass(item)) {
                if (item.quality > 0) {
                    item.quality--;
                }
            } else {
                if (item.quality < 50) {
                    item.quality++;

                    if (isBackstagePass(item)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality++;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality++;
                            }
                        }
                    }
                }
            }

            updateSellIn(item);

            if (item.sellIn < 0) {
                if (!"Good Wine".equals(item.name)) {
                    if (!isBackstagePass(item)) {
                        if (item.quality > 0) {
                            item.quality--;
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality++;
                    }
                }
            }
        }
    }

    private void updateSellIn(Item item) {
        item.sellIn--;
    }

    private boolean isBackstagePass(Item item) {
        return "Backstage passes for Re:Factor".equals(item.name)
                || "Backstage passes for HAXX".equals(item.name);
    }
}