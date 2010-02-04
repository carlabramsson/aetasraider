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

import org.aetas.dao.activity.CharacterAchiActivityDao;
import org.aetas.model.activity.CharacterAchiActivity;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-04
 * Time: 13:07:39
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CharacterAchiActivityJpaDao extends GenericCharacterActivityJpaDao<CharacterAchiActivity>
        implements CharacterAchiActivityDao {
    @Override
    protected Class<CharacterAchiActivity> getPersistentClass() {
        return CharacterAchiActivity.class;
    }
}
