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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LinkingSessionManager {
  private final Map<String, LinkingSession> sessions = new HashMap<>();
  private final String botUsername;

  public LinkingSessionManager(String botUsername) {
    this.botUsername = botUsername;
  }

  public LinkingSession createSession(Long telegramId) {
    LinkingSession session = new LinkingSession(telegramId);
    this.sessions.put(session.getUuid(), session);
    return session;
  }

  public Optional<LinkingSession> getSession(String uuid) {
    LinkingSession session = this.sessions.get(uuid);
    if (session != null && !session.isExpired()) {
      return Optional.of(session);
    }
    this.sessions.remove(uuid);
    return Optional.empty();
  }

  public void removeSession(String uuid) {
    this.sessions.remove(uuid);
  }

  public void cleanupExpiredSessions() {
    this.sessions.entrySet().removeIf(entry -> entry.getValue().isExpired());
  }

  public String generateInviteLink(LinkingSession session) {
    return String.format("https://t.me/%s?start=link_%s", this.botUsername, session.getUuid());
  }
}
