/*
 * This file is part of AetasRaider.
 *
 *     AetasRaider is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     AetasRaider is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with AetasRaider.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.aetas.test;

import junit.framework.Assert;
import org.aetas.feedprocessors.ItemInfoParser;
import org.aetas.model.item.ItemInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-03
 * Time: 18:46:15
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class TestItemInfoParser {

    @Autowired
    private ItemInfoParser itemInfoParser;

    @Test
    public void simpleTest() {
        ItemInfo itemInfo = itemInfoParser.getItemInfo(1);
        System.out.println(itemInfo);

        Assert.assertEquals("inv_gauntlets_53", itemInfo.getIconName());
        Assert.assertEquals(new Integer(48462), itemInfo.getItemId());
        Assert.assertEquals(new Integer(245), itemInfo.getItemLvl());
        Assert.assertEquals("Hellscream's Handguards of Triumph", itemInfo.getName());
        Assert.assertEquals(new Integer(4), itemInfo.getQuality());
        Assert.assertEquals("Plate", itemInfo.getType());
        Assert.assertEquals("Vault of Archavon", itemInfo.getDropCreatureArea());
        Assert.assertEquals(new Integer(3), itemInfo.getDropCreatureClassification());
        Assert.assertEquals(new Integer(1), itemInfo.getDropCreatureHeroic());
        Assert.assertEquals(new Integer(35360), itemInfo.getDropCreatureId());
        Assert.assertEquals("Koralon the Flame Watcher", itemInfo.getDropCreatureName());
        Assert.assertEquals(true, itemInfo.isSoldByVendor());
    }

}
