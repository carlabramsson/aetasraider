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

package org.aetas.dao.jpa.activity;

import org.aetas.dao.activity.GenericCharacterActivityDao;
import org.aetas.dao.jpa.GenericJpaDao;
import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterActivity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-03
 * Time: 22:10:28
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericCharacterActivityJpaDao<T extends CharacterActivity>
        extends GenericJpaDao<T> implements GenericCharacterActivityDao<T> {


    private String getEntityName() {
        return getPersistentClass().getName();
    }

    public List<T> findAll(WowCharacter wowCharacter) {
        return createQuery("select ac from " + getEntityName() + " ac where ac.wowCharacterId = :wcid  order by ac.date desc")
                .setParameter("wcid", wowCharacter.getId())
                .getResultList();
    }


    public List<T> findRecent(int max) {
        return createQuery("select ac from " + getEntityName() + " ac  order by ac.date desc")
                .setMaxResults(max).getResultList();
    }
}
