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

import org.aetas.dao.activity.CharacterBossKillActivityDao;
import org.aetas.model.InstanceArea;
import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterBossKillActivity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 12:36:24
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CharacterBossKillActivityJpaDao
        extends GenericCharacterActivityJpaDao<CharacterBossKillActivity> implements CharacterBossKillActivityDao {

    @Override
    protected Class<CharacterBossKillActivity> getPersistentClass() {
        return CharacterBossKillActivity.class;
    }

    @Override
    public List<CharacterBossKillActivity> findKillsByInstance(InstanceArea instanceArea, WowCharacter wowCharacter) {
        return getEntityManager().
                createQuery("select ac from CharacterBossKillActivity ac where ac.instanceName = :instanceName and ac.wowCharacterId = :wcId order by ac.date asc")
                .setParameter("instanceName", instanceArea.getName())
                .setParameter("wcId", wowCharacter.getId())
                .getResultList();

    }

    @Override
    public List<CharacterBossKillActivity> findKillsByInstance(InstanceArea instanceArea) {
        return getEntityManager().
                createQuery("select ac from CharacterBossKillActivity ac where ac.instanceName = :instanceName order by ac.date asc")
                .setParameter("instanceName", instanceArea.getName())
                .getResultList();

    }
}
