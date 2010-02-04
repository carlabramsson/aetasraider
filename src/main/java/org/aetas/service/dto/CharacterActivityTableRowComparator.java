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

import java.util.Comparator;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-04
 * Time: 16:27:25
 * To change this template use File | Settings | File Templates.
 */
public class CharacterActivityTableRowComparator<T extends CharacterActivity> implements Comparator<CharacterActivityDateTableRow<T>> {

    private Date dateSlot;

    public CharacterActivityTableRowComparator(Date dateSlot) {
        this.dateSlot = dateSlot;
    }

    @Override
    public int compare(CharacterActivityDateTableRow<T> o1, CharacterActivityDateTableRow<T> o2) {
        Integer o1Size = o1.getActivitiesByDate(dateSlot).size();
        Integer o2Size = o2.getActivitiesByDate(dateSlot).size();

        return o1Size.compareTo(o2Size);
    }
}
