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

import org.aetas.AetasEngine;
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
 * Date: 2010-jan-30
 * Time: 21:50:58
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/engine")
public class EngineController {

    @Autowired
    private AetasEngine aetasEngine;

    @Autowired
    CharacterService characterService;

    @RequestMapping(method = RequestMethod.GET)
    public String getNormalView() {

        return "engine/view";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String resetDatabase() {
        aetasEngine.resetDatabase();

        return "engine/view";

    }

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public String run() {
        aetasEngine.run();

        return "engine/view";

    }

    @RequestMapping(value = "/processchar/{id}")
    public String processChar(@PathVariable String id, Model model) {
        aetasEngine.runForChar(characterService.findById(id));

        return "engine/view";

    }

}
