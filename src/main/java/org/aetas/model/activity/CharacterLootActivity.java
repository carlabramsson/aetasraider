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

package org.aetas.model.activity;

import org.aetas.model.PersistentObject;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 12:23:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class CharacterLootActivity extends AbstractCharacterActivity implements PersistentObject {

    @Id
    private String id;

    @Basic(optional = false)
    @Column(nullable = false)
    private Date date;

    @Basic(optional = false)
    @Column(nullable = false)
    private String wowCharacterId;

    @Basic(optional = false)
    @Column(nullable = false)
    private String lootName;

    @Basic(optional = false)
    @Column(nullable = false)
    private Long itemId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String getWowCharacterId() {
        return wowCharacterId;
    }

    @Override
    public void setWowCharacterId(String wowCharacterId) {
        this.wowCharacterId = wowCharacterId;
    }

    public String getLootName() {
        return lootName;
    }

    public void setLootName(String lootName) {
        this.lootName = lootName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CharacterLootActivity");
        sb.append("{id='").append(id).append('\'');
        sb.append(", date=").append(date);
        sb.append(", wowCharacterId='").append(wowCharacterId).append('\'');
        sb.append(", lootName='").append(lootName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
