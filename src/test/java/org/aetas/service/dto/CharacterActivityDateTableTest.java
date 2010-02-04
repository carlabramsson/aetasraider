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

package org.aetas.service.dto;

import junit.framework.Assert;
import org.aetas.model.activity.CharacterBossKillActivity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-04
 * Time: 15:36:31
 * To change this template use File | Settings | File Templates.
 */
public class CharacterActivityDateTableTest {


    @Test
    public void test_row_with_3_slots() {

        Long now = 100l;

        Date oldestDate = new Date(now - 30);
        Date middleDate = new Date(now - 20);
        Date mostRecentDate = new Date(now - 10);
        ArrayList<Date> dateSlots = new ArrayList<Date>();
        dateSlots.add(oldestDate);
        dateSlots.add(middleDate);
        dateSlots.add(mostRecentDate);

        CharacterActivityDateTableRow<CharacterBossKillActivity> tableRow = new CharacterActivityDateTableRow<CharacterBossKillActivity>(dateSlots);

        // Recent
        tableRow.addActivity(createActivity(now - 1));
        tableRow.addActivity(createActivity(now - 2));

        // Middle
        tableRow.addActivity(createActivity(now - 11));
        tableRow.addActivity(createActivity(now - 12));
        tableRow.addActivity(createActivity(now - 13));
        // Oldest
        tableRow.addActivity(createActivity(now - 21));
        tableRow.addActivity(createActivity(now - 22));
        tableRow.addActivity(createActivity(now - 23));
        tableRow.addActivity(createActivity(now - 24));
        // To old
        tableRow.addActivity(createActivity(now - 31));


        Assert.assertEquals(4, tableRow.getActivitiesByDate(oldestDate).size());
        Assert.assertEquals(3, tableRow.getActivitiesByDate(middleDate).size());
        Assert.assertEquals(2, tableRow.getActivitiesByDate(mostRecentDate).size());

        int i = 0;
        for (Date date : tableRow.getDateSlots()) {
            Assert.assertEquals(dateSlots.get(i).getTime(), date.getTime());
            i++;
        }

    }

    public CharacterBossKillActivity createActivity(long date) {
        CharacterBossKillActivity activity1 = new CharacterBossKillActivity();
        activity1.setBossName(UUID.randomUUID().toString());
        activity1.setId(UUID.randomUUID().toString());
        activity1.setInstanceName(UUID.randomUUID().toString());
        activity1.setWowCharacterId(UUID.randomUUID().toString());

        activity1.setDate(new Date(date));

        return activity1;
    }
}
