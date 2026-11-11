package com.gildedrose;

class SulfurasRule extends ItemRule {
    @Override
    protected void updateQualityDaily(Item item) {
        // Do nothing
    }

    @Override
    protected void updateQualityAfterSellIn(Item item) {
        // Do nothing
    }

    @Override
    protected boolean isLegendary() {
        return true;
    }
}
