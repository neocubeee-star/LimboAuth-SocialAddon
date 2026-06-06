/*
 * Copyright (C) 2022 - 2025 Elytrium
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.elytrium.limboauth.socialaddon.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginHistoryManager {
  private final Map<String, List<LoginHistory>> playerHistory = new HashMap<>();
  private final int maxHistorySize;

  public LoginHistoryManager(int maxHistorySize) {
    this.maxHistorySize = maxHistorySize;
  }

  public void addLogin(String nickname, String ip, String location, boolean suspicious) {
    String lowercaseNick = nickname.toLowerCase();
    List<LoginHistory> history = this.playerHistory.computeIfAbsent(lowercaseNick, k -> new ArrayList<>());
    history.add(new LoginHistory(ip, location, LocalDateTime.now(), suspicious));

    if (history.size() > this.maxHistorySize) {
      history.remove(0);
    }
  }

  public List<LoginHistory> getHistory(String nickname) {
    String lowercaseNick = nickname.toLowerCase();
    return Collections.unmodifiableList(this.playerHistory.getOrDefault(lowercaseNick, new ArrayList<>()));
  }

  public LoginHistory getLastLogin(String nickname) {
    String lowercaseNick = nickname.toLowerCase();
    List<LoginHistory> history = this.playerHistory.get(lowercaseNick);
    if (history == null || history.isEmpty()) {
      return null;
    }
    return history.get(history.size() - 1);
  }

  public void clearHistory(String nickname) {
    String lowercaseNick = nickname.toLowerCase();
    this.playerHistory.remove(lowercaseNick);
  }
}
