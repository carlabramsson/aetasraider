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

package org.aetas.service.dto;

import org.aetas.model.activity.CharacterActivity;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-04
 * Time: 15:00:45
 * To change this template use File | Settings | File Templates.
 */
public class CharacterActivityDateTableRow<T extends CharacterActivity> implements Comparable<CharacterActivityDateTableRow<T>> {

    private final TreeMap<Date, List<T>> activities = new TreeMap<Date, List<T>>();
    private final List<Date> dateSlots;


    public CharacterActivityDateTableRow(List<Date> dateSlots) {
        this.dateSlots = dateSlots;
        for (Date date : dateSlots) {
            this.activities.put(date, new ArrayList<T>());
        }
    }

    public void addActivity(T activity) {
        Map.Entry<Date, List<T>> entry = activities.floorEntry(activity.getDate());
        if (entry != null)
            entry.getValue().add(activity);
    }

    public List<T> getActivitiesByDate(Date date) {
        return Collections.unmodifiableList(activities.get(date));
    }

    public List<Date> getDateSlots() {
        return Collections.unmodifiableList(dateSlots);
    }

    public SortedMap<Date, List<T>> getActivities() {
        return Collections.unmodifiableSortedMap(activities);
    }

    @Override
    public int compareTo(CharacterActivityDateTableRow<T> o) {
        return new CharacterActivityTableRowComparator<T>(dateSlots.get(0)).compare(this, o);
    }


}
