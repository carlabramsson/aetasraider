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

import org.aetas.model.activity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-03
 * Time: 22:08:54
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CharacterActivityDao {
    @Autowired
    private CharacterAchiActivityDao characterAchiActivityDao;
    @Autowired
    private CharacterAchiStepActivityDao characterAchiStepActivityDao;
    @Autowired
    private CharacterBossKillActivityDao characterBossKillActivityDao;
    @Autowired
    private CharacterInstanceClearedActivityDao characterInstanceClearedActivityDao;
    @Autowired
    private CharacterLootActivityDao characterLootActivityDao;
    @Autowired
    private UnknownCharacterActivityDao unknownCharacterActivityDao;

    public CharacterActivity persist(CharacterActivity activity) {

        if (activity instanceof CharacterAchiActivity)
            return characterAchiActivityDao.persist((CharacterAchiActivity) activity);
        else if (activity instanceof CharacterAchiStepActivity)
            return characterAchiStepActivityDao.persist((CharacterAchiStepActivity) activity);
        else if (activity instanceof CharacterBossKillActivity)
            return characterBossKillActivityDao.persist((CharacterBossKillActivity) activity);
        else if (activity instanceof CharacterInstanceClearedActivity)
            return characterInstanceClearedActivityDao.persist((CharacterInstanceClearedActivity) activity);
        else if (activity instanceof CharacterLootActivity)
            return characterLootActivityDao.persist((CharacterLootActivity) activity);
        else if (activity instanceof UnknownCharacterActivity)
            return unknownCharacterActivityDao.persist((UnknownCharacterActivity) activity);
        else
            throw new RuntimeException("Unknown conrete activity class " + activity.getClass());

    }

}
