package com.gildedrose;

class BackstagePassRule extends ItemRule {
    @Override
    protected void updateQualityDaily(Item item) {
        changeQualityBy(item, 1);

        if (item.sellIn <= 10) {
            changeQualityBy(item, 1);
        }
        if (item.sellIn <= 5) {
            changeQualityBy(item, 1);
        }
    }

    @Override
    protected void updateQualityAfterSellIn(Item item) {
        item.quality = 0;
    }
}
