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

package org.aetas.dao.activity;

import org.aetas.dao.GenericDao;
import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterActivity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-03
 * Time: 22:15:17
 * To change this template use File | Settings | File Templates.
 */
public interface GenericCharacterActivityDao<T extends CharacterActivity> extends GenericDao<T> {

    public List<T> findAll(WowCharacter wowCharacter);

    public List<T> findRecent(int max);

}
