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

import org.aetas.feedprocessors.CharacterActivityFeedParser;
import org.aetas.model.WowCharacter;
import org.aetas.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-jan-16
 * Time: 17:52:29
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/character")
public class CharacterController extends AbstractController {

    @Autowired
    CharacterService characterService;

    @Autowired
    CharacterActivityFeedParser characterActivityFeedParser;


    @RequestMapping(method = RequestMethod.GET)
    public String getCreateForm(Model model) {
        model.addAttribute(new WowCharacter());
        model.addAttribute("allChars", characterService.getAllWowCharacters());
        return "character/list";
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getView(@PathVariable String id, Model model) {
        WowCharacter wowCharacter = characterService.findById(id);
        if (wowCharacter == null) {
            throw new ResourceNotFoundException(id);
        }
        model.addAttribute("lootActivities", characterService.findAllCharacterLootActivity(wowCharacter));
        model.addAttribute("bossKills", characterService.findAllCharacterBossKillActivities(wowCharacter));
        model.addAttribute(wowCharacter);
        return "character/view";
    }

}
