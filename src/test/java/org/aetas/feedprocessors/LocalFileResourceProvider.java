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

package org.aetas.feedprocessors;

import org.aetas.model.WowCharacter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-26
 * Time: 23:30:40
 * To change this template use File | Settings | File Templates.
 */
public class LocalFileResourceProvider implements ResourceProvider {


    @Override
    public InputStream getXmlGuildInfoInputStream() {
        try {
            return new File("src/test/resources/guild-info.xml").toURI().toURL().openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public URL createCharFeedUrl(WowCharacter wowCharacter) {
        try {
            return new File("src/test/resources/character-activity-feed-jarviz.xml").toURI().toURL();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
