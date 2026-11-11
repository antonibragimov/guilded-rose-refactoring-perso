package com.gildedrose;

class BackstagePassRule extends ItemRule {

    private static final int DAYS_LEFT_FOR_DOUBLE_INCREASE = 10;
    private static final int DAYS_LEFT_FOR_TRIPLE_INCREASE = 5;

    @Override
    protected void updateQualityDaily(Item item) {
        changeQualityBy(item, 1);

        if (item.sellIn <= DAYS_LEFT_FOR_DOUBLE_INCREASE) {
            changeQualityBy(item, 1);
        }
        if (item.sellIn <= DAYS_LEFT_FOR_TRIPLE_INCREASE) {
            changeQualityBy(item, 1);
        }
    }

    @Override
    protected void updateQualityAfterSellIn(Item item) {
        item.quality = MIN_QUALITY;
    }
}
