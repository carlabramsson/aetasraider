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
import org.aetas.feedprocessors.GuildInfoFeedParser;
import org.aetas.model.WowCharacter;
import org.jdom.JDOMException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-26
 * Time: 22:53:28
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class TestArmoryGuildInfo {


    @Test
    public void test_parse_guild_feed() throws IOException, SAXException, JDOMException {

        GuildInfoFeedParser p = new GuildInfoFeedParser();

        List<WowCharacter> characterList = p.getGuildMembers();
        for (WowCharacter wChar : characterList)
            System.out.println(wChar);

        Assert.assertEquals(139, characterList.size());

        Assert.assertEquals("Hippolyte", characterList.get(138).getName());
        Assert.assertEquals(new Integer(80), characterList.get(138).getLevel());


    }


}
