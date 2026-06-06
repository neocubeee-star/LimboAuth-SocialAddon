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

package net.elytrium.limboauth.socialaddon.admin.dto;

import java.util.List;

public class AdminSearchResult {
  private final String nickname;
  private final String ip;
  private final String location;
  private final List<String> linkedAccounts;
  private final boolean isOnline;
  private final boolean isBlocked;

  public AdminSearchResult(
      String nickname,
      String ip,
      String location,
      List<String> linkedAccounts,
      boolean isOnline,
      boolean isBlocked
  ) {
    this.nickname = nickname;
    this.ip = ip;
    this.location = location;
    this.linkedAccounts = linkedAccounts;
    this.isOnline = isOnline;
    this.isBlocked = isBlocked;
  }

  public String getNickname() {
    return this.nickname;
  }

  public String getIp() {
    return this.ip;
  }

  public String getLocation() {
    return this.location;
  }

  public List<String> getLinkedAccounts() {
    return this.linkedAccounts;
  }

  public boolean isOnline() {
    return this.isOnline;
  }

  public boolean isBlocked() {
    return this.isBlocked;
  }

  public String formatForMessage() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("👤 Игрок: %s\n", this.nickname));
    sb.append(String.format("🌐 IP: %s\n", this.ip != null ? this.ip : "Unknown"));
    sb.append(String.format("📍 Местоположение: %s\n", this.location != null ? this.location : "Unknown"));
    sb.append(String.format("🟢 Статус: %s\n", this.isOnline ? "🟢 Онлайн" : "🔴 Оффлайн"));
    sb.append(String.format("🔒 Блокировка: %s\n", this.isBlocked ? "Включена" : "Отключена"));
    
    if (!this.linkedAccounts.isEmpty()) {
      sb.append(String.format("📋 Привязанные аккаунты (%d):\n", this.linkedAccounts.size()));
      for (String account : this.linkedAccounts) {
        sb.append(String.format("  • %s\n", account));
      }
    }
    
    return sb.toString();
  }
}
