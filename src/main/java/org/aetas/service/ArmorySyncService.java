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

import org.aetas.dao.CharacterActivityDao;
import org.aetas.dao.CharacterDao;
import org.aetas.feedprocessors.CharacterActivityFeedParser;
import org.aetas.feedprocessors.GuildInfoFeedParser;
import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterActivity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-30
 * Time: 17:13:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ArmorySyncService {

    private static final Log log = LogFactory.getLog(ArmorySyncService.class);

    @Autowired
    private CharacterDao characterDao;

    @Autowired
    private CharacterActivityDao characterActivityDao;

    @Autowired
    private GuildInfoFeedParser guildInfoFeedParser;

    @Autowired
    private CharacterActivityFeedParser characterActivityFeedParser;

    /**
     * Gets the members of the guild from the Armory and persists
     * members not yet in the local storage.
     * Returns a list of the new chars
     *
     * @return a list of the new guild members
     */
    public List<WowCharacter> fetchAndSyncGuildMembers() {
        List<WowCharacter> charsFromFeed = guildInfoFeedParser.getGuildMembers();
        List<WowCharacter> persistedChars = new ArrayList<WowCharacter>();

        for (WowCharacter wowCharacter : charsFromFeed) {
            WowCharacter persistentWowCharacter = characterDao.fetchWowCharacterByName(wowCharacter.getName());
            if (persistentWowCharacter == null) {
                wowCharacter.setSavedDate(new Date());
                persistentWowCharacter = characterDao.persist(wowCharacter);
                log.info("Persisted new wowChar " + persistentWowCharacter);
                persistedChars.add(persistentWowCharacter);
            } else if (!persistentWowCharacter.attributesEquals(wowCharacter)) {
                persistentWowCharacter.copyAttributesFrom(wowCharacter);
                characterDao.persist(persistentWowCharacter);
                log.info("Updated wowChar " + persistentWowCharacter);
            }

        }
        return persistedChars;
    }


    /**
     * Fetches all activity for a WoWCharacter from Armory and saves it
     * to the local storage. Does not calculate anything or match against the calendar
     *
     * @param wowCharacter the persistent WowCharacter to fetch activities
     * @return a list of the new activities since last sync
     */
    public List<CharacterActivity> fetchAndSyncWowCharacter(WowCharacter wowCharacter) {
        if (wowCharacter.getId() == null)
            throw new RuntimeException("The character is not persistent: " + wowCharacter);
        log.info("Searching for new activies for char " + wowCharacter.getName() + " with date limit " + wowCharacter.getMostRecentSavedActivity());
        List<CharacterActivity> characterActivities = characterActivityFeedParser.getCharacterActivityFromFeed(wowCharacter, wowCharacter.getMostRecentSavedActivity());


        if (characterActivities != null && characterActivities.size() > 0) {
            for (CharacterActivity characterActivity : characterActivities) {
                characterActivity.setWowCharacterId(wowCharacter.getId());
                characterActivityDao.persist(characterActivity);
            }
            wowCharacter.setMostRecentSavedActivity(characterActivities.get(0).getDate());
            wowCharacter.setActivityFeedLastChecked(new Date());
            log.info("Synced " + characterActivities.size() + " for char " + wowCharacter.getName());
            characterDao.persist(wowCharacter);
        }


        return characterActivities;

    }
}
