package com.gildedrose;

abstract class ItemRule {

    protected static final int MIN_QUALITY = 0;
    protected static final int MAX_QUALITY = 50;

    protected abstract void updateQualityDaily(Item item);
    protected abstract void updateQualityAfterSellIn(Item item);

    public final void applyTo(Item item) {
        if (!isLegendary()) {
            updateQualityDaily(item);
            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                updateQualityAfterSellIn(item);
            }
        }
    }

    protected void changeQualityBy(Item item, int step) {
        int updatedQuality = item.quality + step;
        if (step > 0) {
            if (item.quality < MAX_QUALITY) {
                item.quality = Math.min(MAX_QUALITY, updatedQuality);
            }
        } else {
            if (item.quality > MIN_QUALITY) {
                item.quality = Math.max(MIN_QUALITY, updatedQuality);
            }
        }
    }

    protected boolean isLegendary() {
        return false;
    }
}
