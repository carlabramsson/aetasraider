<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  ~ This file is part of AetasRaider.
  ~
  ~     AetasRaider is free software: you can redistribute it and/or modify
  ~     it under the terms of the GNU General Public License as published by
  ~     the Free Software Foundation, either version 3 of the License, or
  ~     (at your option) any later version.
  ~
  ~     AetasRaider is distributed in the hope that it will be useful,
  ~     but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~     GNU General Public License for more details.
  ~
  ~     You should have received a copy of the GNU General Public License
  ~     along with AetasRaider.  If not, see <http://www.gnu.org/licenses/>.
  --%>

<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title><fmt:message key="welcome.title"/></title>
    <script src="http://static.wowhead.com/widgets/power.js"></script>
</head>
<body>
<div class="container">
    <h1>
        <fmt:message key="character.title"/>
    </h1>
    ${wowCharacter.id}<br>
    ${wowCharacter.name}<br>
    <br>

    <b>Loot</b>
    <c:forEach var="lootActivity" items="${lootActivities}">
        ${lootActivity.date} <a
            href="http://www.wowhead.com/?item=${lootActivity.itemId}">${lootActivity.lootName}</a><br>
    </c:forEach>
    <br>
    <b>Kills</b>
    <c:forEach var="bossKill" items="${bossKills}">
        ${bossKill.date} ${bossKill.bossName} in ${bossKill.instanceName}<br>
    </c:forEach>

</div>
</body>
</html>