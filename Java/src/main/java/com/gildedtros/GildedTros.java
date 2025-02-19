package com.gildedtros;

class GildedTros {
    Item[] items;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Good Wine")
                    && !item.name.equals("Backstage passes for Re:Factor")
                    && !item.name.equals("Backstage passes for HAXX")) {
                if (item.quality > 0) {
                    if (!item.name.equals("B-DAWG Keychain")) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals("Backstage passes for Re:Factor") || item.name.equals("Backstage passes for HAXX")) {
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

            if (!item.name.equals("B-DAWG Keychain")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Good Wine")) {
                    if (!item.name.equals("Backstage passes for Re:Factor") && !item.name.equals("Backstage passes for HAXX")) {
                        if (item.quality > 0) {
                            if (!item.name.equals("B-DAWG Keychain")) {
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