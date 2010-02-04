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

import org.aetas.model.InstanceArea;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-04
 * Time: 13:41:22
 * To change this template use File | Settings | File Templates.
 */
@Service
public class InstanceAreaService {

    private Set<InstanceArea> instanceAreaSet = new HashSet<InstanceArea>();

    public InstanceAreaService() {
        instanceAreaSet.add(new InstanceArea("Icecrown 10 player", 10, "Icecrown"));
        instanceAreaSet.add(new InstanceArea("Icecrown 25 player", 25, "Icecrown"));

    }

    public InstanceAreaService(Set<InstanceArea> instanceAreaSet) {
        this.instanceAreaSet = instanceAreaSet;
    }

    public InstanceArea getByName(String name) {
        for (InstanceArea instanceArea : instanceAreaSet) {
            if (instanceArea.getName().equals(name))
                return instanceArea;
        }
        return null;
    }
}
