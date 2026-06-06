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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiAccountManager {
  private final Map<Long, Set<String>> telegramToAccounts = new HashMap<>();
  private final Map<String, Long> accountToTelegram = new HashMap<>();
  private final int maxAccounts;

  public MultiAccountManager(int maxAccounts) {
    this.maxAccounts = maxAccounts;
  }

  public boolean linkAccount(String nickname, Long telegramId) {
    String lowercaseNick = nickname.toLowerCase();

    if (this.accountToTelegram.containsKey(lowercaseNick)) {
      return false;
    }

    Set<String> accounts = this.telegramToAccounts.computeIfAbsent(telegramId, k -> new HashSet<>());
    if (accounts.size() >= this.maxAccounts) {
      return false;
    }

    accounts.add(lowercaseNick);
    this.accountToTelegram.put(lowercaseNick, telegramId);
    return true;
  }

  public boolean unlinkAccount(String nickname) {
    String lowercaseNick = nickname.toLowerCase();
    Long telegramId = this.accountToTelegram.remove(lowercaseNick);

    if (telegramId != null) {
      Set<String> accounts = this.telegramToAccounts.get(telegramId);
      if (accounts != null) {
        accounts.remove(lowercaseNick);
        if (accounts.isEmpty()) {
          this.telegramToAccounts.remove(telegramId);
        }
      }
      return true;
    }
    return false;
  }

  public List<String> getAccounts(Long telegramId) {
    Set<String> accounts = this.telegramToAccounts.get(telegramId);
    if (accounts == null) {
      return Collections.emptyList();
    }
    return new ArrayList<>(accounts);
  }

  public Long getTelegramId(String nickname) {
    return this.accountToTelegram.get(nickname.toLowerCase());
  }

  public boolean isLinked(String nickname) {
    return this.accountToTelegram.containsKey(nickname.toLowerCase());
  }

  public int getAccountCount(Long telegramId) {
    Set<String> accounts = this.telegramToAccounts.get(telegramId);
    return accounts == null ? 0 : accounts.size();
  }
}
