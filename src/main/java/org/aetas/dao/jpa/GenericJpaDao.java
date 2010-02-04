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

import org.aetas.dao.GenericDao;
import org.aetas.model.PersistentObject;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 12:29:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericJpaDao<T extends PersistentObject> implements GenericDao<T> {


    @PersistenceContext
    private EntityManager em;


    protected abstract Class<T> getPersistentClass();

    @Override
    public void delete(T entity) {
        if (entity.getId() == null)
            throw new RuntimeException("Entity is not persistent, had no id " + entity);

        em.remove(find(entity.getId()));
    }

    @Override
    @Transactional
    public T persist(T entity) {

        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }


    }

    @Override
    public T find(String id) {
        return em.find(getPersistentClass(), id);
    }

    protected Query createQuery(String query) {
        return em.createQuery(query);
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
