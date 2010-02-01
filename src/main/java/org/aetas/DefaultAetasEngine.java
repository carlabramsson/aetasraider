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

package org.aetas;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions;
import org.aetas.model.WowCharacter;
import org.aetas.service.ArmorySyncService;
import org.aetas.service.CharacterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-30
 * Time: 20:48:38
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DefaultAetasEngine implements AetasEngine {

    private static final Log log = LogFactory.getLog(DefaultAetasEngine.class);

    @Autowired
    private ArmorySyncService armorySyncService;

    @Autowired
    private CharacterService characterService;


    @Override
    public void run() {
        log.info("Running ArmorySync");
        runArmorySync();

    }

    public void runForChar(WowCharacter wowCharacter) {
        log.info("Starting armory sync for char " + wowCharacter);
        armorySyncService.fetchAndSyncWowCharacter(wowCharacter);
    }

    private void runArmorySync() {
        log.info("Armory sync started. Syncing guildmembers");
        armorySyncService.fetchAndSyncGuildMembers();

        Queue queue = QueueFactory.getDefaultQueue();


        for (WowCharacter wowCharacter : characterService.getAllWowCharacters()) {
            queue.add(TaskOptions.Builder.url("/engine/processchar/" + wowCharacter.getId()));
        }

    }

    @Override
    public void resetDatabase() {
        log.warn("Reset database called");
        for (WowCharacter wowCharacter : characterService.getAllWowCharacters())
            characterService.deleteCharacter(wowCharacter);

    }
}
