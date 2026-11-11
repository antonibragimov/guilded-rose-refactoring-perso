package com.gildedrose;

class ConjuredRule extends ItemRule {
    @Override
    protected void updateQualityDaily(Item item) {
        changeQualityBy(item, -2);
    }

    @Override
    protected void updateQualityAfterSellIn(Item item) {
        changeQualityBy(item, -2);
    }
}
