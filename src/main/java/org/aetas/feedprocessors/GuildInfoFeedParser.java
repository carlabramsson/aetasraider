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
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-26
 * Time: 23:15:13
 * To change this template use File | Settings | File Templates.
 */
@Component
public class GuildInfoFeedParser {

    @Autowired
    ResourceProvider resourceProvider;

    private static SAXBuilder builder = new SAXBuilder(false);


    public List<WowCharacter> getGuildMembers() {

        ArrayList<WowCharacter> wowCharactersList = new ArrayList<WowCharacter>();

        Document doc;
        try {
            doc = fileToDocument(resourceProvider);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Object charElementObj : doc.getRootElement().getChild("guildInfo").getChild("guild").getChild("members").getChildren("character")) {
            wowCharactersList.add(parseWowCharacter((Element) charElementObj));
        }
        return wowCharactersList;

    }

    private WowCharacter parseWowCharacter(Element element) {
        WowCharacter wowCharacter = new WowCharacter();

        wowCharacter.setName(element.getAttributeValue("name"));
        wowCharacter.setLevel(Integer.parseInt(element.getAttributeValue("level")));
        wowCharacter.setGenderId(Integer.parseInt(element.getAttributeValue("genderId")));
        wowCharacter.setClassId(Integer.parseInt(element.getAttributeValue("classId")));
        wowCharacter.setRaceId(Integer.parseInt(element.getAttributeValue("raceId")));
        wowCharacter.setRank(Integer.parseInt(element.getAttributeValue("rank")));
        wowCharacter.setAchPoints(Integer.parseInt(element.getAttributeValue("achPoints")));

        return wowCharacter;
    }

    private Document fileToDocument(ResourceProvider resourceProvider) throws JDOMException, IOException {
        Document d = null;
        builder.setExpandEntities(false);
        builder.setFeature(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        d = builder.build(resourceProvider.getXmlGuildInfoInputStream());
        return d;
    }
}
