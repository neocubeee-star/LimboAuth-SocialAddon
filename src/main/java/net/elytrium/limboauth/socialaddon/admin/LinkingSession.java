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
import java.util.UUID;

public class LinkingSession {
  private final String uuid;
  private final Long telegramId;
  private final LocalDateTime createdAt;
  private String nickname;

  public LinkingSession(Long telegramId) {
    this.uuid = UUID.randomUUID().toString();
    this.telegramId = telegramId;
    this.createdAt = LocalDateTime.now();
  }

  public String getUuid() {
    return this.uuid;
  }

  public Long getTelegramId() {
    return this.telegramId;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public String getNickname() {
    return this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(this.createdAt.plusMinutes(15));
  }
}
