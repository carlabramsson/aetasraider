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

package org.aetas.model.item;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-03
 * Time: 18:30:28
 * To change this template use File | Settings | File Templates.
 */
public class ItemInfo {

    private Integer itemId;

    private Integer itemLvl;

    private String name;

    private String iconName;

    private Integer quality;

    private String type;

    private Integer dropCreatureId;

    private String dropCreatureName;

    private String dropCreatureArea;

    private Integer dropCreatureClassification;

    private Integer dropCreatureHeroic;

    private boolean soldByVendor = false;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemLvl() {
        return itemLvl;
    }

    public void setItemLvl(Integer itemLvl) {
        this.itemLvl = itemLvl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDropCreatureId() {
        return dropCreatureId;
    }

    public void setDropCreatureId(Integer droppedByCreatureId) {
        this.dropCreatureId = droppedByCreatureId;
    }

    public String getDropCreatureName() {
        return dropCreatureName;
    }

    public void setDropCreatureName(String dropCreatureName) {
        this.dropCreatureName = dropCreatureName;
    }

    public String getDropCreatureArea() {
        return dropCreatureArea;
    }

    public void setDropCreatureArea(String droppedInArea) {
        this.dropCreatureArea = droppedInArea;
    }

    public Integer getDropCreatureClassification() {
        return dropCreatureClassification;
    }

    public void setDropCreatureClassification(Integer classification) {
        this.dropCreatureClassification = classification;
    }

    public Integer getDropCreatureHeroic() {
        return dropCreatureHeroic;
    }

    public void setDropCreatureHeroic(Integer heroic) {
        this.dropCreatureHeroic = heroic;
    }

    public boolean isSoldByVendor() {
        return soldByVendor;
    }

    public void setSoldByVendor(boolean soldByVendor) {
        this.soldByVendor = soldByVendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemInfo)) return false;

        ItemInfo itemInfo = (ItemInfo) o;

        if (itemId != null ? !itemId.equals(itemInfo.itemId) : itemInfo.itemId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return itemId != null ? itemId.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ItemInfo");
        sb.append("{itemId=").append(itemId);
        sb.append(", itemLvl=").append(itemLvl);
        sb.append(", name='").append(name).append('\'');
        sb.append(", iconName='").append(iconName).append('\'');
        sb.append(", quality=").append(quality);
        sb.append(", type='").append(type).append('\'');
        sb.append(", dropCreatureId=").append(dropCreatureId);
        sb.append(", dropCreatureName='").append(dropCreatureName).append('\'');
        sb.append(", dropCreatureArea='").append(dropCreatureArea).append('\'');
        sb.append(", dropCreatureClassification=").append(dropCreatureClassification);
        sb.append(", dropCreatureHeroic=").append(dropCreatureHeroic);
        sb.append(", soldByVendor=").append(soldByVendor);
        sb.append('}');
        return sb.toString();
    }
}
