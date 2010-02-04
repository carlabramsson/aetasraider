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

package org.aetas.service;

import org.aetas.feedprocessors.ItemInfoParser;
import org.aetas.model.item.ItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-03
 * Time: 20:51:24
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ItemService {

    @Autowired
    private ItemInfoParser itemInfoParser;

    private Cache cache;


    public ItemService() {
        try {
            cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
        } catch (CacheException e) {
            throw new RuntimeException(e);
        }
    }

    public ItemInfo getItemInfo(int itemNumber) {
        ItemInfo itemInfo = (ItemInfo) cache.get(itemNumber);
        if (itemInfo == null) {
            itemInfo = itemInfoParser.getItemInfo(itemNumber);
            if (itemInfo != null)
                cache.put(itemNumber, itemInfo);
        }
        return itemInfo;
    }
}
