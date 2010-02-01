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

import org.aetas.feedprocessors.CharacterActivityFeedParser;
import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 13:01:35
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class TestFeedparsing {

    @Autowired
    CharacterActivityFeedParser characterActivityFeedParser;

    @Test
    public void testSomething() {

        System.out.println("Hello test!");

        WowCharacter jarviz = new WowCharacter();
        jarviz.setName("Jarviz");


        List<CharacterActivity> activity = characterActivityFeedParser.getCharacterActivityFromFeed(jarviz);

        for (CharacterActivity entry : activity)
            System.out.println(entry);

    }
}
