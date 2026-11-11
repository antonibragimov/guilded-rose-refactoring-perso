package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private static final String ELIXIR_MONGOOSE = "Elixir of the Mongoose";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    private static final int SULFURAS_QUALITY = 80;
    private static final int COMMON_MAX_QUALITY = 50;

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    /*
     * Normal item
     */

    @Test
    void testNormalItem_degradesByOne() {
        // Setup
        String itemName = "item";
        int sellInInit = 10;
        int qualityInit = 10;

        int sellInExpected = 9;
        int qualityExpected = 9;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testNormalItem_degradesByOne_dayBeforeSellIn() {
        // Setup
        String itemName = "item";
        int sellInInit = 1;
        int qualityInit = 2;

        int sellInExpected = 0;
        int qualityExpected = 1;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testNormalItem_degradesByTwoAfterSellIn() {
        // Setup
        String itemName = "item";
        int sellInInit = 0;
        int qualityInit = 10;

        int sellInExpected = -1;
        int qualityExpected = 8;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testNormalItem_qualityNeverNegative() {
        // Setup
        String itemName = "item";
        int sellInInit = 1;
        int qualityInit = 0;

        int sellInExpected = 0;
        int qualityExpected = 0;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testNormalItem_qualityNeverNegativeAfterSellIn() {
        // Setup
        String itemName = "item";
        int sellInInit = 0;
        int qualityInit = 1;

        int sellInExpected = -1;
        int qualityExpected = 0;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testNormalItem_degradesByOne_from50() {
        // Setup
        String itemName = "item";
        int sellInInit = 10;

        int sellInExpected = 9;
        int qualityExpected = 49;

        Item item = new Item(itemName, sellInInit, COMMON_MAX_QUALITY);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    /*
     * Aged Brie
     */

    @Test
    void testAgedBrie_increasesByOne_dayBeforeSellIn() {
        // Setup
        String itemName = AGED_BRIE;
        int sellInInit = 1;
        int qualityInit = 5;

        int sellInExpected = 0;
        int qualityExpected = 6;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testAgedBrie_qualityNeverAbove50() {
        // Setup
        String itemName = AGED_BRIE;
        int sellInInit = 10;
        int sellInExpected = 9;

        Item item = new Item(itemName, sellInInit, COMMON_MAX_QUALITY);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, COMMON_MAX_QUALITY);
    }

    @Test
    void testAgedBrie_increasesByTwoAfterSellIn() {
        // Setup
        String itemName = AGED_BRIE;
        int sellInInit = 0;
        int qualityInit = 10;

        int sellInExpected = -1;
        int qualityExpected = 12;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testAgedBrie_qualityNeverAbove50AfterSellIn() {
        // Setup
        String itemName = AGED_BRIE;
        int sellInInit = 0;
        int qualityInit = 49;

        int sellInExpected = -1;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, COMMON_MAX_QUALITY);
    }

    @Test
    void testAgedBrie_increasesByOne_from0() {
        // Setup
        String itemName = AGED_BRIE;
        int sellInInit = 10;
        int qualityInit = 0;

        int sellInExpected = 9;
        int qualityExpected = 1;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    /*
     * Sulfuras
     */

    @Test
    void testSulfuras_neverChanges() {
        // Setup
        String itemName = SULFURAS;
        int sellInInit = 10;

        int sellInExpected = 10;

        Item item = new Item(itemName, sellInInit, SULFURAS_QUALITY);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, SULFURAS_QUALITY);
    }

    @Test
    void testSulfuras_neverChangesAfterSellIn() {
        // Setup
        String itemName = SULFURAS;
        int sellInInit = -1;

        int sellInExpected = -1;

        Item item = new Item(itemName, sellInInit, SULFURAS_QUALITY);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, SULFURAS_QUALITY);
    }

    /*
     * Backstage Passes
     */

    @Test
    void testBackstagePasses_increaseByOne_moreThan10Days() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 15;
        int qualityInit = 15;

        int sellInExpected = 14;
        int qualityExpected = 16;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testBackstagePasses_increaseByTwo_10DaysOrLess() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 10;
        int qualityInit = 10;

        int sellInExpected = 9;
        int qualityExpected = 12;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testBackstagePasses_increaseByThree_5DaysOrLess() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 5;
        int qualityInit = 5;

        int sellInExpected = 4;
        int qualityExpected = 8;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testBackstagePasses_increaseByThree_dayBeforeConcert() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 1;
        int qualityInit = 14;

        int sellInExpected = 0;
        int qualityExpected = 17;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testBackstagePasses_dropToZeroAfterConcert() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 0;
        int qualityInit = 10;

        int sellInExpected = -1;
        int qualityExpected = 0;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testBackstagePasses_qualityNeverAbove50_5DaysOrLess() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 5;
        int qualityInit = 49;

        int sellInExpected = 4;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, COMMON_MAX_QUALITY);
    }

    @Test
    void testBackstagePasses_qualityNeverAbove50_10DaysOrLess() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 10;
        int qualityInit = 49;

        int sellInExpected = 9;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, COMMON_MAX_QUALITY);
    }

    @Test
    void testBackstagePasses_qualityNeverAbove50_moreThan10Days() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 40;

        int sellInExpected = 39;

        Item item = new Item(itemName, sellInInit, COMMON_MAX_QUALITY);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, COMMON_MAX_QUALITY);
    }

    @Test
    void testBackstagePasses_increaseByTwo_6Days() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 6;
        int qualityInit = 30;

        int sellInExpected = 5;
        int qualityExpected = 32;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    @Test
    void testBackstagePasses_increaseByOne_11Days() {
        // Setup
        String itemName = BACKSTAGE_PASSES;
        int sellInInit = 11;
        int qualityInit = 20;

        int sellInExpected = 10;
        int qualityExpected = 21;

        Item item = new Item(itemName, sellInInit, qualityInit);

        // Exercise & Verify
        exerciseVerifyItem(item, itemName, sellInExpected, qualityExpected);
    }

    /*
     * Multiple items
     */

    @Test
    void testMultipleItems_allItemsUpdated() {
        // Setup
        int sellInInit1 = 10;
        int sellInExpected1 = 10;

        int sellInInit2 = 5;
        int qualityInit2 = 5;

        int sellInExpected2 = 4;
        int qualityExpected2 = 4;

        Item[] items = new Item[] {
            new Item(SULFURAS, sellInInit1, SULFURAS_QUALITY),
            new Item(ELIXIR_MONGOOSE, sellInInit2, qualityInit2),
        };

        // Exercise
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // Verify
        assertEquals(SULFURAS, app.items[0].name);
        assertEquals(sellInExpected1, app.items[0].sellIn);
        assertEquals(SULFURAS_QUALITY, app.items[0].quality);

        assertEquals(ELIXIR_MONGOOSE, app.items[1].name);
        assertEquals(sellInExpected2, app.items[1].sellIn);
        assertEquals(qualityExpected2, app.items[1].quality);
    }

    /*
     * Helper methods
     */

    private void exerciseVerifyItem(
        Item item,
        String nameExpected,
        int sellInExpected,
        int qualityExpected) {

        Item[] items = new Item[] { item };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(nameExpected, app.items[0].name);
        assertEquals(sellInExpected, app.items[0].sellIn);
        assertEquals(qualityExpected, app.items[0].quality);
    }
}
