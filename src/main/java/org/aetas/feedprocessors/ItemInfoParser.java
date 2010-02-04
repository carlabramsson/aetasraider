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

import org.aetas.model.item.ItemInfo;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-03
 * Time: 18:14:17
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ItemInfoParser {

    @Autowired
    private ResourceProvider resourceProvider;

    private static SAXBuilder builder = new SAXBuilder(false);


    public ItemInfo getItemInfo(int itemNumber) {
        ItemInfo itemInfo = new ItemInfo();
        Document xmlDoc;

        try {
            xmlDoc = getItemInfoXmlFromArmory(itemNumber);
        } catch (JDOMException e) {
            throw new RuntimeException("Error while getting xml from armory for item " + itemNumber, e);
        } catch (IOException e) {
            throw new RuntimeException("Error while getting xml from armory for item " + itemNumber, e);
        }

        Element itemElement = xmlDoc.getRootElement().getChild("itemInfo").getChild("item");
        itemInfo.setItemId(Integer.parseInt(itemElement.getAttributeValue("id")));
        itemInfo.setItemLvl(Integer.parseInt(itemElement.getAttributeValue("level")));
        itemInfo.setIconName(itemElement.getAttributeValue("icon"));
        itemInfo.setName(itemElement.getAttributeValue("name"));
        itemInfo.setType(itemElement.getAttributeValue("type"));
        itemInfo.setQuality(Integer.parseInt(itemElement.getAttributeValue("quality")));

        // dropped by
        Element dropCreatures = itemElement.getChild("dropCreatures");
        if (dropCreatures != null) {
            List<Element> creatures = dropCreatures.getChildren("creature");
            if (creatures != null && creatures.size() > 0)
                for (Object creatureElementObj : creatures) {
                    Element creatureElement = (Element) creatureElementObj;
                    itemInfo.setDropCreatureArea(creatureElement.getAttributeValue("area"));
                    itemInfo.setDropCreatureClassification(Integer.parseInt(creatureElement.getAttributeValue("classification")));
                    itemInfo.setDropCreatureHeroic(Integer.parseInt(creatureElement.getAttributeValue("heroic")));
                    itemInfo.setDropCreatureId(Integer.parseInt(creatureElement.getAttributeValue("id")));
                    itemInfo.setDropCreatureName(creatureElement.getAttributeValue("name"));
                }
        }

        // sold by
        Element vendorsElement = itemElement.getChild("vendors");
        if (vendorsElement != null) {
            List vendorCreaturesElementList = vendorsElement.getChildren("creature");
            itemInfo.setSoldByVendor(vendorCreaturesElementList != null && vendorCreaturesElementList.size() > 0);

        }

        return itemInfo;


    }

    private Document getItemInfoXmlFromArmory(int itemId) throws JDOMException, IOException {
        Document d = null;
        builder.setExpandEntities(false);
        builder.setFeature(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        d = builder.build(resourceProvider.getArmoryItemInfoXml(itemId));
        return d;
    }
}
