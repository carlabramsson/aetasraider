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

package org.aetas.dao.jpa;

import org.aetas.dao.CharacterActivityDao;
import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterActivity;
import org.aetas.model.activity.CharacterBossKillActivity;
import org.aetas.model.activity.CharacterLootActivity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 12:36:24
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CharacterActivityJpaDao extends GenericJpaDao<CharacterActivity> implements CharacterActivityDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected Class<CharacterActivity> getPersistentClass() {
        return CharacterActivity.class;
    }

    public List<CharacterActivity> findAllCharacterActivities(WowCharacter wowCharacter) {
        throw new RuntimeException("Not impl");

    }

    @Override
    public List<CharacterLootActivity> findAllCharacterLootActivities(WowCharacter wowCharacter) {
        return em.createQuery("select ac from CharacterLootActivity ac where ac.wowCharacterId = :wcid order by ac.date desc")
                .setParameter("wcid", wowCharacter.getId())
                .getResultList();
    }

    @Override
    public List<CharacterBossKillActivity> findAllCharacterBossKillActivity(WowCharacter wowCharacter) {
        return em.createQuery("select ac from CharacterBossKillActivity ac where ac.wowCharacterId = :wcid  order by ac.date desc")
                .setParameter("wcid", wowCharacter.getId())
                .getResultList();
    }

    @Override
    public List<CharacterBossKillActivity> findRecentCharacterBossKillActivities(int max) {
        return em.createQuery("select ac from CharacterBossKillActivity ac  order by ac.date desc")
                .setMaxResults(max).getResultList();
    }
}
