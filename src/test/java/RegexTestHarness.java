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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarness {

    public static void main(String[] args) {


        /*
        String matcherString = "Obtained <a id=\"i=50324&#38;cn=Jarviz&#38;r=The Venture Co&#38;s=2\" href=\"/item-info.xml?i=50324\"><span class=\"stats_rarity4\">[Lightsworn Shoulderplates]</span></a>. <strong>(Equipped)</strong>";

        Pattern pattern =
                Pattern.compile("item-info.xml\\?i=([0-9]+)\"");
        */

        String matcherString = "1 Times completed the Trial of the Crusader (10 player).";
        String matcherString1 = "14 Mal&#39;Ganis defeated (Heroic CoT: Stratholme).";

        Pattern pattern =
                Pattern.compile("([0-9]+) Times completed the (.*)\\.");


        Matcher matcher =
                pattern.matcher(matcherString);

        boolean found = false;
        while (matcher.find()) {
            System.out.format("I found the text \"%s\" starting at " +
                    "index %d and ending at index %d.%n",
                    matcher.group(), matcher.start(), matcher.end());
            found = true;
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));


        }
        if (!found) {
            System.out.format("No match found.%n");
        }

    }
}