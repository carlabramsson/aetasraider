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

import org.aetas.dao.activity.UnknownCharacterActivityDao;
import org.aetas.model.activity.UnknownCharacterActivity;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-04
 * Time: 13:15:03
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UnknownCharacterActivityJpaDao extends GenericCharacterActivityJpaDao<UnknownCharacterActivity>
        implements UnknownCharacterActivityDao {

    @Override
    protected Class<UnknownCharacterActivity> getPersistentClass() {
        return UnknownCharacterActivity.class;
    }
}
