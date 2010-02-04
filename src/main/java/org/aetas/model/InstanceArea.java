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

package org.aetas.model;

/**
 * Created by IntelliJ IDEA.
 * User: carlabramsson
 * Date: 2010-feb-04
 * Time: 13:39:37
 * To change this template use File | Settings | File Templates.
 */
public class InstanceArea {

    private String name;
    private int size;
    private String zoneName;

    public InstanceArea(String name, int size, String zoneName) {
        this.name = name;
        this.size = size;
        this.zoneName = zoneName;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getZoneName() {
        return zoneName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstanceArea)) return false;

        InstanceArea that = (InstanceArea) o;

        if (size != that.size) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (zoneName != null ? !zoneName.equals(that.zoneName) : that.zoneName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + size;
        result = 31 * result + (zoneName != null ? zoneName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("InstanceArea");
        sb.append("{name='").append(name).append('\'');
        sb.append(", size=").append(size);
        sb.append(", zoneName='").append(zoneName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
