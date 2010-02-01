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

package org.aetas.dao;

import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterActivity;
import org.aetas.model.activity.CharacterBossKillActivity;
import org.aetas.model.activity.CharacterLootActivity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-30
 * Time: 18:04:01
 * To change this template use File | Settings | File Templates.
 */
public interface CharacterActivityDao extends GenericDao<CharacterActivity> {

    public List<CharacterActivity> findAllCharacterActivities(WowCharacter wowCharacter);

    public List<CharacterLootActivity> findAllCharacterLootActivities(WowCharacter wowCharacter);

    public List<CharacterBossKillActivity> findAllCharacterBossKillActivity(WowCharacter wowCharacter);

    List<CharacterBossKillActivity> findRecentCharacterBossKillActivities(int max);
}
