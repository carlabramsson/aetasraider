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

package org.aetas.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 12:21:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class WowCharacter implements PersistentObject {

    @Id
    private String id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer level;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer classId;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer genderId;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer raceId;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer rank;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer achPoints;

    @Basic
    private Date activityFeedLastChecked;

    @Basic
    private Date mostRecentSavedActivity;

    @Basic
    private Date savedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getActivityFeedLastChecked() {
        return activityFeedLastChecked;
    }

    public void setActivityFeedLastChecked(Date activityFeedLastChecked) {
        this.activityFeedLastChecked = activityFeedLastChecked;
    }

    public Date getMostRecentSavedActivity() {
        return mostRecentSavedActivity;
    }

    public void setMostRecentSavedActivity(Date mostRecentSavedActivity) {
        this.mostRecentSavedActivity = mostRecentSavedActivity;
    }

    public Date getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getAchPoints() {
        return achPoints;
    }

    public void setAchPoints(Integer achPoints) {
        this.achPoints = achPoints;
    }

    /**
     * Copies all attributes except name, id and timestamps to this
     * instance from the supplied instance. Useful for updates.
     *
     * @param wowCharacter the
     */
    public void copyAttributesFrom(final WowCharacter wowCharacter) {
        this.level = wowCharacter.level;
        this.classId = wowCharacter.classId;
        this.genderId = wowCharacter.genderId;
        this.rank = wowCharacter.rank;
        this.raceId = wowCharacter.raceId;
        this.achPoints = wowCharacter.achPoints;

    }


    public boolean attributesEquals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WowCharacter)) return false;

        WowCharacter that = (WowCharacter) o;

        if (achPoints != null ? !achPoints.equals(that.achPoints) : that.achPoints != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (genderId != null ? !genderId.equals(that.genderId) : that.genderId != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (raceId != null ? !raceId.equals(that.raceId) : that.raceId != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;

        return true;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("WowCharacter");
        sb.append("{id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
