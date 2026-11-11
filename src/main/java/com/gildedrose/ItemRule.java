package com.gildedrose;

abstract class ItemRule {

    protected abstract void updateQualityDaily(Item item);
    protected abstract void updateQualityAfterSellIn(Item item);

    public final void applyTo(Item item) {
        updateQualityDaily(item);
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            updateQualityAfterSellIn(item);
        }
    }

    protected void changeQualityBy(Item item, int step) {
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
}
