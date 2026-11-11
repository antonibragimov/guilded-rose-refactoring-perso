package com.gildedrose;

import static com.gildedrose.ItemRule.changeQualityBy;

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
                ItemRule rule = new AgedBrieRule();
                rule.applyTo(item);
            } else if (isBackstagePass(item.name)) {
                ItemRule rule = new BackstagePassRule();
                rule.applyTo(item);
            } else if (isConjured(item.name)) {
                applyConjuredRule(item);
            } else {
                ItemRule rule = new NormalRule();
                rule.applyTo(item);
            }
        }
    }

    private void applyConjuredRule(Item item) {
        changeQualityBy(item, -2);
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            changeQualityBy(item, -2);
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

    private static boolean isConjured(String itemName) {
        return itemName.startsWith("Conjured");
    }
}
