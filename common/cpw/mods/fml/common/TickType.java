/*
 * The FML Forge Mod Loader suite.
 * Copyright (C) 2012 cpw
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

package cpw.mods.fml.common;

import java.util.EnumSet;

public enum TickType {
    /**
     * Fired during the world evaluation loop
     * server and client side
     * 
     * arg 0 : a float zero (the "render time")
     * arg 1 : The world that is ticking
     */
    WORLD,
    /**
     * client side
     * Fired during the render processing phase
     * arg 0 : float "partial render time"
     */
    RENDER,
    /**
     * client side
     * Fired during the render processing phase if a GUI is open
     * arg 0 : float "partial render time"
     * arg 1 : the open gui
     */
    GUI,
    /**
     * client side
     * Fired during the world evaluation loop if a gui is open
     * arg 0 : The open gui
     */
    WORLDGUI,
    /**
     * client side
     * Fired once as the world loads from disk
     */
    WORLDLOADTICK,
    /**
     * client side
     * Fired once as the world loads from disk
     * arg 0 : the open gui
     */
    GUILOADTICK;

    /**
     * Partner ticks that are also cancelled by returning false from onTickInGame
     * 
     * @return
     */
    public EnumSet<TickType> partnerTicks()
    {
        if (this==WORLD) return EnumSet.of(RENDER,WORLDLOADTICK);
        if (this==RENDER) return EnumSet.of(WORLD, WORLDLOADTICK);
        if (this==GUI) return EnumSet.of(WORLDGUI, GUILOADTICK);
        if (this==WORLDGUI) return EnumSet.of(GUI, GUILOADTICK);
        if (this==WORLDLOADTICK) return EnumSet.of(WORLD, RENDER);
        if (this==GUILOADTICK) return EnumSet.of(GUI, WORLDGUI);
        return null;
    }
}