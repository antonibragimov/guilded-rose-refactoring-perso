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
                changeQualityBy(item, 1);
                item.sellIn = item.sellIn - 1;
                if (item.sellIn < 0) {
                    changeQualityBy(item, 1);
                }
            } else if (isBackstagePass(item.name)) {
                changeQualityBy(item, 1);
                if (item.sellIn < 11) {
                    changeQualityBy(item, 1);
                }
                if (item.sellIn < 6) {
                    changeQualityBy(item, 1);
                }
                item.sellIn = item.sellIn - 1;
                if (item.sellIn < 0) {
                    item.quality = 0;
                }
            } else {
                changeQualityBy(item, -1);
                item.sellIn = item.sellIn - 1;
                if (item.sellIn < 0) {
                    changeQualityBy(item, -1);
                }
            }
        }
    }

    private static void changeQualityBy(Item item, int step) {
        int updatedQuality = item.quality + step;
        if (step > 0) {
            if (item.quality < 50) {
                item.quality = Math.min(50, updatedQuality);
            }
        } else {
            if (item.quality > 0) {
                item.quality = Math.max(0, updatedQuality);
            }
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
