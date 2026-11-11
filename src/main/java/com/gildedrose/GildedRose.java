package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (isSulfuras(item.name)) {
                continue;
            }

            if (isAgedBrie(item.name)) {
                increaseQualityByOne(item);
                item.sellIn = item.sellIn - 1;
                if (item.sellIn < 0) {
                    increaseQualityByOne(item);
                }
            } else if (isBackstagePass(item.name)) {
                increaseQualityByOne(item);
                if (item.sellIn < 11) {
                    increaseQualityByOne(item);
                }
                if (item.sellIn < 6) {
                    increaseQualityByOne(item);
                }
                item.sellIn = item.sellIn - 1;
                if (item.sellIn < 0) {
                    item.quality = 0;
                }
            } else {
                decreaseQualityByOne(item);
                item.sellIn = item.sellIn - 1;
                if (item.sellIn < 0) {
                    decreaseQualityByOne(item);
                }
            }
        }
    }

    private static void decreaseQualityByOne(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private static void increaseQualityByOne(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private static boolean isSulfuras(String itemName) {
        return itemName.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isAgedBrie(String itemName) {
        return itemName.equals("Aged Brie");
    }

    private static boolean isBackstagePass(String itemName) {
        return itemName.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
