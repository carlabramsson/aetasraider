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

import com.google.appengine.api.datastore.Text;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-30
 * Time: 11:06:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class CharacterAchiActivity extends AbstractCharacterActivity {

    @Id
    private String id;

    @Basic(optional = false)
    @Column(nullable = false)
    private Date date;

    @Basic(optional = false)
    @Column(nullable = false)
    private String wowCharacterId;

    @Basic
    private String achiName;

    @Basic
    private Text content;

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

    public String getAchiName() {
        return achiName;
    }

    public void setAchiName(String achiName) {
        this.achiName = achiName;
    }

    public String getContent() {
        return (content != null) ? content.getValue() : null;
    }

    public void setContent(String content) {
        this.content = new Text(content);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CharacterAchiActivity");
        sb.append("{id='").append(id).append('\'');
        sb.append(", date=").append(date);
        sb.append(", wowCharacterId='").append(wowCharacterId).append('\'');
        sb.append(", achiName='").append(achiName).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
