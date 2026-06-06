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

import java.util.List;
import java.util.Locale;
import net.elytrium.limboauth.socialaddon.admin.dto.AdminSearchResult;

public class AdminCommandProcessor {
  private final AdminManager adminManager;
  private final MultiAccountManager multiAccountManager;
  private final LoginHistoryManager loginHistoryManager;

  public AdminCommandProcessor(
      AdminManager adminManager,
      MultiAccountManager multiAccountManager,
      LoginHistoryManager loginHistoryManager
  ) {
    this.adminManager = adminManager;
    this.multiAccountManager = multiAccountManager;
    this.loginHistoryManager = loginHistoryManager;
  }

  public String searchPlayerByNickname(String nickname) {
    String lowercaseNick = nickname.toLowerCase(Locale.ROOT);
    return String.format("🔍 Поиск игрока: %s\n", nickname);
  }

  public String searchPlayerByIp(String ip) {
    return String.format("🌐 IP: %s\n", ip);
  }

  public AdminSearchResult getPlayerInfo(String nickname) {
    String lowercaseNick = nickname.toLowerCase(Locale.ROOT);
    Long telegramId = this.multiAccountManager.getTelegramId(lowercaseNick);
    List<String> linkedAccounts = telegramId != null 
        ? this.multiAccountManager.getAccounts(telegramId) 
        : List.of();
    
    return new AdminSearchResult(
        lowercaseNick,
        null,
        null,
        linkedAccounts,
        false,
        false
    );
  }

  public List<LoginHistory> getLoginHistory(String nickname) {
    return this.loginHistoryManager.getHistory(nickname);
  }

  public AdminManager getAdminManager() {
    return this.adminManager;
  }

  public MultiAccountManager getMultiAccountManager() {
    return this.multiAccountManager;
  }

  public LoginHistoryManager getLoginHistoryManager() {
    return this.loginHistoryManager;
  }
}
