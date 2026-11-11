package com.gildedrose;

class NormalRule extends ItemRule {
    @Override
    protected void updateQualityDaily(Item item) {
        changeQualityBy(item, -1);
    }

    @Override
    protected void updateQualityAfterSellIn(Item item) {
        changeQualityBy(item, -1);
    }
}
