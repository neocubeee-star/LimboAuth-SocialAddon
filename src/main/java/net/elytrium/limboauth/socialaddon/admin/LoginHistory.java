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
import java.time.format.DateTimeFormatter;

public class LoginHistory {
  private final String ip;
  private final String location;
  private final LocalDateTime loginTime;
  private final boolean suspicious;

  public LoginHistory(String ip, String location, LocalDateTime loginTime, boolean suspicious) {
    this.ip = ip;
    this.location = location;
    this.loginTime = loginTime;
    this.suspicious = suspicious;
  }

  public String getIp() {
    return this.ip;
  }

  public String getLocation() {
    return this.location;
  }

  public LocalDateTime getLoginTime() {
    return this.loginTime;
  }

  public boolean isSuspicious() {
    return this.suspicious;
  }

  public String formatForMessage() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    return String.format(
        "🌐 IP: %s\n📍 Местоположение: %s\n🕒 Время: %s%s",
        this.ip,
        this.location.isEmpty() ? "Unknown" : this.location,
        this.loginTime.format(formatter),
        this.suspicious ? " ⚠️ ПОДОЗРИТЕЛЬНЫЙ ВХОД" : ""
    );
  }
}
