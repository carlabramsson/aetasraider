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

package org.aetas.feedprocessors;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.aetas.model.WowCharacter;
import org.aetas.model.activity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 12:13:54
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CharacterActivityFeedParser {

    private static final Log log = LogFactory.getLog(CharacterActivityFeedParser.class);

    @Autowired
    ResourceProvider resourceProvider;

    private final Pattern lootNameRegExp =
            Pattern.compile("Obtained \\[(.*)\\]");

    private final Pattern itemNumberRegExp =
            Pattern.compile("item-info.xml\\?i=([0-9]+)\"");

    private final Pattern bossKillRegExp = Pattern.compile("([0-9]+) (.*) (kills|victories|escapes|defeated) \\((.*)\\)");
    private final Pattern bossKillAlt2RegExp =
            Pattern.compile("([0-9]+) Victories over (.*) \\((.*)\\)");

    private final Pattern instanceClearActivityRegExp = Pattern.compile("([0-9]+) Times completed the (.*)\\.");

    private final Pattern charAchiRegExp =
            Pattern.compile("Earned the achievement \\[(.*)\\]");
    private final Pattern featOfStrengthRegExp =
            Pattern.compile("Earned the feat of strength \\[(.*)\\]");


    private final Pattern charAchiStepRegExp =
            Pattern.compile("Completed step \\[(.*)\\] of achievement \\[(.*)\\]");

    private final String[] ignoreList = new String[]{"Mr. Bigglesworth kills."};
    private static final String ERROR_TITLE_NO_ENTRIES = "There are no entries that match the filter parameters.";

    public List<CharacterActivity> getCharacterActivityFromFeed(final WowCharacter wowCharacter) {
        return getCharacterActivityFromFeed(wowCharacter, null);
    }

    public List<CharacterActivity> getCharacterActivityFromFeed(final WowCharacter wowCharacter, final Date onlyAfterDate) {

        final ArrayList<CharacterActivity> characterActivities = new ArrayList<CharacterActivity>();

        final SyndFeed feed = fetchCharFeed(wowCharacter, 3);

        for (Object syndEntryObj : feed.getEntries()) {
            final SyndEntry syndEntry = (SyndEntry) syndEntryObj;
            if (ERROR_TITLE_NO_ENTRIES.equals(syndEntry.getTitle())) {
                log.warn("Found error in title for char " + wowCharacter.getName());
            } else if (onlyAfterDate != null && !syndEntry.getPublishedDate().after(onlyAfterDate)) {
                log.debug("Activity is after " + onlyAfterDate);
                return characterActivities;
            } else {

                final CharacterActivity characterActivity = parseCharacterActivity(syndEntry);
                if (characterActivity != null) {
                    characterActivity.setWowCharacterId(wowCharacter.getId());
                    parseAndSetDate(syndEntry, characterActivity);
                    characterActivities.add(characterActivity);
                } else {
                    log.warn("Unknown synd-entry " + ((SyndEntry) syndEntryObj).getTitle());
                }
            }
        }

        return characterActivities;

    }

    private CharacterActivity parseCharacterActivity(final SyndEntry syndEntry) {
        CharacterActivity characterActivity;
        String title = "";
        String content = "";

        if (syndEntry.getTitle() != null)
            title = syndEntry.getTitle().trim();

        if (syndEntry.getContents().size() > 0)
            content = ((SyndContent) syndEntry.getContents().get(0)).getValue();


        for (String ignoreString : ignoreList)
            if (title.contains(ignoreString)) {
                log.info("Ignored :" + title);
                return null;
            } else if (content.contains(ignoreString)) {
                log.info("Ignored :" + content);
                return null;
            }

        characterActivity = parseCharacterAchiStepActivity(title, content);
        if (characterActivity != null) return characterActivity;

        characterActivity = parseCharacterLootActivity(title, content);
        if (characterActivity != null) return characterActivity;

        characterActivity = parseCharacterBossKillActivity(title, content);
        if (characterActivity != null) return characterActivity;

        characterActivity = parseCharacterAchiActivity(title, content);
        if (characterActivity != null) return characterActivity;

        characterActivity = parseCharacterInstanceClearedActivity(title, content);
        if (characterActivity != null) return characterActivity;


        return parseUnknownCharacterActivity(title, content);


    }

    /**
     * Generic parser that works for everything. Used when no other parser matches.
     *
     * @param title
     * @param content
     * @return
     */
    private UnknownCharacterActivity parseUnknownCharacterActivity(final String title, final String content) {
        final UnknownCharacterActivity characterActivity = new UnknownCharacterActivity();
        characterActivity.setTitle(title);
        characterActivity.setContent(content);

        return characterActivity;
    }


    private CharacterBossKillActivity parseCharacterBossKillActivity(final String title, final String content) {
        if (content == null)
            return null;
        Matcher bossKillMatcher = bossKillRegExp.matcher(content);
        int instanceNameGroup = 4;

        if (!bossKillMatcher.find()) {
            bossKillMatcher = bossKillAlt2RegExp.matcher(content);
            if (!bossKillMatcher.find())
                return null;
            else
                instanceNameGroup = 2;
        }


        final CharacterBossKillActivity characterActivity = new CharacterBossKillActivity();
        characterActivity.setNumberOfKills(Integer.parseInt(bossKillMatcher.group(1)));
        characterActivity.setBossName(bossKillMatcher.group(2));
        characterActivity.setInstanceName(bossKillMatcher.group(instanceNameGroup));

        return characterActivity;
    }

    private CharacterInstanceClearedActivity parseCharacterInstanceClearedActivity(final String title, final String content) {
        if (content == null)
            return null;
        final Matcher m = instanceClearActivityRegExp.matcher(content);
        if (!m.find())
            return null;

        final CharacterInstanceClearedActivity activity = new CharacterInstanceClearedActivity();
        activity.setNumberOfClears(Integer.parseInt(m.group(1)));
        activity.setInstanceName(m.group(2));

        return activity;
    }

    private CharacterLootActivity parseCharacterLootActivity(final String title, final String content) {
        final Matcher lootNameMatcher = lootNameRegExp.matcher(title);
        if (!lootNameMatcher.find())
            return null;

        final CharacterLootActivity characterActivity = new CharacterLootActivity();
        characterActivity.setLootName(lootNameMatcher.group(1));

        if (content != null) {
            final Matcher matcher = itemNumberRegExp.matcher(content);
            if (matcher.find())
                characterActivity.setItemId(Long.parseLong(matcher.group(1)));
        } else {
            System.err.println("Content was null for loot activity with title " + title);
        }


        return characterActivity;


    }

    private CharacterAchiActivity parseCharacterAchiActivity(final String title, final String content) {
        Matcher m = charAchiRegExp.matcher(title);

        if (!m.find()) {
            m = featOfStrengthRegExp.matcher(title);
            if (!m.find())
                return null;
        }

        final CharacterAchiActivity characterAchiActivity = new CharacterAchiActivity();
        characterAchiActivity.setAchiName(m.group(1));
        characterAchiActivity.setContent(content);


        return characterAchiActivity;
    }

    private CharacterAchiStepActivity parseCharacterAchiStepActivity(final String title, final String content) {
        final Matcher m = charAchiStepRegExp.matcher(title);

        if (!m.find())
            return null;
        final CharacterAchiStepActivity activity = new CharacterAchiStepActivity();
        activity.setStepName(m.group(1));
        activity.setAchiName(m.group(2));
        activity.setContent(content);

        return activity;
    }

    private void parseAndSetDate(final SyndEntry syndEntry, final CharacterActivity characterActivity) {
        characterActivity.setDate(syndEntry.getUpdatedDate());
    }

    private SyndFeed fetchCharFeed(final WowCharacter wowCharacter, int retries) {

        URL feedUrl = resourceProvider.createCharFeedUrl(wowCharacter);

        SyndFeedInput input = new SyndFeedInput();
        try {
            return input.build(new XmlReader(feedUrl)); // Todo: switch to inputStream
        } catch (FeedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            if (retries < 1)
                throw new RuntimeException(e);
            else {
                log.warn("Exception (prob timeout) while w8ing for charFeed, will retry.");
                return fetchCharFeed(wowCharacter, retries - 1);
            }
        }
    }


}
