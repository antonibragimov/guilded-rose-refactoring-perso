package com.gildedrose;

final class ItemRuleFactory {
    private static final ItemRule AGED_BRIE_RULE = new AgedBrieRule();
    private static final ItemRule BACKSTAGE_PASS_RULE = new BackstagePassRule();
    private static final ItemRule SULFURAS_RULE = new SulfurasRule();
    private static final ItemRule NORMAL_RULE = new NormalRule();
    private static final ItemRule CONJURED_RULE = new ConjuredRule();

    private ItemRuleFactory() {
    }

    public static ItemRule ruleFor(String itemName) {
        if (isAgedBrie(itemName)) {
            return AGED_BRIE_RULE;
        } else if (isBackstagePass(itemName)) {
            return BACKSTAGE_PASS_RULE;
        } else if (isSulfuras(itemName)) {
            return SULFURAS_RULE;
        } else if (isConjured(itemName)) {
            return CONJURED_RULE;
        }
        return NORMAL_RULE;
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
