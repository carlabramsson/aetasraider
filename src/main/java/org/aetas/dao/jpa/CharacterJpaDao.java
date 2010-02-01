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

import org.aetas.dao.CharacterDao;
import org.aetas.model.WowCharacter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 12:33:13
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CharacterJpaDao extends GenericJpaDao<WowCharacter> implements CharacterDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected Class<WowCharacter> getPersistentClass() {
        return WowCharacter.class;
    }

    @Override
    public WowCharacter fetchWowCharacterByName(String name) {
        try {
            return (WowCharacter) em.createQuery("select wc from WowCharacter wc where wc.name = :name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<WowCharacter> getAllWowCharacters() {

        ArrayList<WowCharacter> chars = new ArrayList<WowCharacter>();
        List<WowCharacter> lazyList = (List<WowCharacter>) em.createQuery("select wc from WowCharacter wc").getResultList();
        for (WowCharacter wowCharacter : lazyList) {
            wowCharacter.getName();
            chars.add(wowCharacter);
        }
        return chars;


    }
}
