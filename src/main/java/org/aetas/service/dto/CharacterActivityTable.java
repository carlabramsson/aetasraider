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
 * Time: 16:02:47
 * To change this template use File | Settings | File Templates.
 */
public class CharacterActivityTable<T extends CharacterActivity> {

    private HashMap<String, CharacterActivityDateTableRow<T>> unsortedTable = new HashMap<String, CharacterActivityDateTableRow<T>>();

    private final List<Date> dateSlots;

    public CharacterActivityTable(List<Date> dateSlots) {
        this.dateSlots = dateSlots;
    }

    public synchronized void add(T activity) {
        CharacterActivityDateTableRow<T> row = unsortedTable.get(activity.getWowCharacterId());
        if (row == null) {
            row = new CharacterActivityDateTableRow<T>(dateSlots);
            unsortedTable.put(activity.getWowCharacterId(), row);
        }
        row.addActivity(activity);
    }

    public synchronized void addAll(Collection<T> activities) {
        for (T ac : activities)
            add(ac);

    }

    public synchronized Map<String, CharacterActivityDateTableRow<T>> getSortedData(int slotNum) {
        Comparator<CharacterActivityDateTableRow<T>> comparator = new CharacterActivityTableRowComparator<T>(dateSlots.get(0));
        ValueComparer<String, CharacterActivityDateTableRow<T>> valueComparer = new ValueComparer<String, CharacterActivityDateTableRow<T>>(unsortedTable, comparator);
        TreeMap<String, CharacterActivityDateTableRow<T>> sortedData = new TreeMap<String, CharacterActivityDateTableRow<T>>(valueComparer);

        sortedData.putAll(unsortedTable);

        return sortedData;

    }

    /**
     * inner class to do soring of the map *
     */
    private static class ValueComparer<K, V> implements Comparator<K> {
        private Map<K, V> data = null;
        private Comparator<V> comparator;

        public ValueComparer(Map<K, V> data, Comparator<V> comparator) {
            this.data = data;
            this.comparator = comparator;
        }

        public int compare(K o1, K o2) {
            V e1 = data.get(o1);
            V e2 = data.get(o2);
            return comparator.compare(e1, e2);
        }
	}
}
