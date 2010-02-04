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

package org.aetas.service;

import org.aetas.dao.CharacterDao;
import org.aetas.dao.activity.*;
import org.aetas.model.InstanceArea;
import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterBossKillActivity;
import org.aetas.model.activity.CharacterLootActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-30
 * Time: 20:56:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CharacterService {

    @Autowired
    private CharacterDao characterDao;

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


    public List<WowCharacter> getAllWowCharacters() {
        return characterDao.getAllWowCharacters();
    }


    public void deleteCharacter(WowCharacter wowCharacter) {
        characterDao.delete(wowCharacter);
    }

    public WowCharacter findById(String id) {
        return characterDao.find(id);
    }


    public List<CharacterLootActivity> findAllCharacterLootActivity(WowCharacter wowCharacter) {
        return characterLootActivityDao.findAll(wowCharacter);
    }

    public List<CharacterBossKillActivity> findAllCharacterBossKillActivities(WowCharacter wowCharacter) {
        return characterBossKillActivityDao.findAll(wowCharacter);
    }

    public List<CharacterBossKillActivity> findRecentCharacterBossKillActivities(int max) {
        return characterBossKillActivityDao.findRecent(max);
    }

    public List<CharacterLootActivity> findRecentCharacterLootActivities(int max) {
        return characterLootActivityDao.findRecent(max);
    }

    public List<CharacterBossKillActivity> findCharacterBossKillActivities(InstanceArea instanceArea) {
        return characterBossKillActivityDao.findKillsByInstance(instanceArea);
    }
}
