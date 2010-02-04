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

package org.aetas.web;

import org.aetas.model.WowCharacter;
import org.aetas.model.activity.CharacterBossKillActivity;
import org.aetas.service.CharacterService;
import org.aetas.service.InstanceAreaService;
import org.aetas.service.dto.CharacterActivityDateTableRow;
import org.aetas.service.dto.CharacterActivityTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-04
 * Time: 14:01:23
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/raideractivity")
public class RaiderActivityController {

    private static final long DAY_IN_MILLIS = 86400000l;

    @Autowired
    InstanceAreaService instanceAreaService;

    @Autowired
    CharacterService characterService;

    @RequestMapping(method = RequestMethod.GET)
    public String getSummary(Model model) {

        List<Date> dateSlots = new ArrayList<Date>();
        dateSlots.add(new Date(System.currentTimeMillis() - (DAY_IN_MILLIS * 7)));
        dateSlots.add(new Date(System.currentTimeMillis() - (DAY_IN_MILLIS * 14)));
        dateSlots.add(new Date(System.currentTimeMillis() - (DAY_IN_MILLIS * 28)));

        List<CharacterBossKillActivity> icc10Kills = characterService.findCharacterBossKillActivities(instanceAreaService.getByName("Icecrown 10 player"));

        CharacterActivityTable<CharacterBossKillActivity> activityTable = new CharacterActivityTable<CharacterBossKillActivity>(dateSlots);
        activityTable.addAll(icc10Kills);

        Map<String, CharacterActivityDateTableRow<CharacterBossKillActivity>> sortedTable = activityTable.getSortedData(0);

        Map<String, WowCharacter> charactersMap = new HashMap<String, WowCharacter>();
        for (String charId : sortedTable.keySet())
            charactersMap.put(charId, characterService.findById(charId));

        model.addAttribute("charactersMap", charactersMap);
        model.addAttribute("activityTableChars", sortedTable.keySet());
        model.addAttribute("activityTable", sortedTable);


        return "raideractivity/summary";
    }


}
