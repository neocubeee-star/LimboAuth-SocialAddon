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

import java.util.HashSet;
import java.util.Set;

public class AdminManager {
  private final Set<Long> adminIds;
  private final int maxLinkedAccounts;

  public AdminManager(Set<Long> adminIds, int maxLinkedAccounts) {
    this.adminIds = new HashSet<>(adminIds);
    this.maxLinkedAccounts = maxLinkedAccounts;
  }

  public boolean isAdmin(long telegramId) {
    return this.adminIds.contains(telegramId);
  }

  public void addAdmin(long telegramId) {
    this.adminIds.add(telegramId);
  }

  public void removeAdmin(long telegramId) {
    this.adminIds.remove(telegramId);
  }

  public int getMaxLinkedAccounts() {
    return this.maxLinkedAccounts;
  }

  public Set<Long> getAdminIds() {
    return new HashSet<>(this.adminIds);
  }
}
