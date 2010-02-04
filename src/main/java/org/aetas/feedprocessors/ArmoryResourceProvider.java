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
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-26
 * Time: 23:37:25
 * To change this template use File | Settings | File Templates.
 */
public class ArmoryResourceProvider implements ResourceProvider {
    @Override
    public InputStream getXmlGuildInfoInputStream() {
        String url = "http://eu.wowarmory.com/guild-info.xml?r=The+Venture+Co&gn=Mayhem";
        return getArmoryXml(url);
    }


    public URL createCharFeedUrl(WowCharacter wowCharacter) {
        try {
            try {
                return new URL("http://eu.wowarmory.com/character-feed.atom?r=The+Venture+Co&cn=" + URLEncoder.encode(wowCharacter.getName(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public InputStream getArmoryItemInfoXml(int itemId) {
        Assert.isTrue(itemId > 0, "itemId was 0 or less");
        return getArmoryXml("http://eu.wowarmory.com/item-info.xml?i=" + itemId);
    }

    private InputStream getArmoryXml(String url) {
        try {

            URL guildInfoUrl = new URL(url);
            HttpURLConnection urlConn = (HttpURLConnection) guildInfoUrl.openConnection();
            urlConn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.2) Gecko/20070219 Firefox/2.0.0.2");
            urlConn.connect();
            return urlConn.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
