package com.gildedtros;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!"Good Wine".equals(item.name)
                    && !"Backstage passes for Re:Factor".equals(item.name)
                    && !"Backstage passes for HAXX".equals(item.name)) {
                if (item.quality > 0) {
                    if (!"B-DAWG Keychain".equals(item.name)) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if ("Backstage passes for Re:Factor".equals(item.name) || "Backstage passes for HAXX".equals(item.name)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!"B-DAWG Keychain".equals(item.name)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!"Good Wine".equals(item.name)) {
                    if (!"Backstage passes for Re:Factor".equals(item.name) && !"Backstage passes for HAXX".equals(item.name)) {
                        if (item.quality > 0) {
                            if (!"B-DAWG Keychain".equals(item.name)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}