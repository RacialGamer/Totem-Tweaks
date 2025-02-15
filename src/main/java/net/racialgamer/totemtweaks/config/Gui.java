package net.racialgamer.totemtweaks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import static me.shedaniel.autoconfig.AutoConfig.getConfigHolder;


@Config(name = "totemtweaks")
public class Gui implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public float totemSize = 1f;

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public float popSize = 0.3f;

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public boolean disableEquipAnimation = false;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.PrefixText
    public boolean TotemPopAnimation = true;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int animationSpeed = 40;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.Tooltip
    public boolean lockRotationPosition = false;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.Tooltip
    public boolean disableRotations = false;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.Tooltip
    public boolean staticSize = false;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int xPosition = 50;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int yPosition = 50;

    @ConfigEntry.Category("Overlay")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public boolean showTotemCount = false;

    @ConfigEntry.Category("Overlay")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public boolean showOverlay = false;

    @ConfigEntry.Category("Overlay")
    @ConfigEntry.ColorPicker
    public int overlayColor = 0xFF0000;

    @ConfigEntry.Category("Overlay")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int overlayOpacity = 128;


    @ConfigEntry.Category("Size Animation")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public boolean enableTotemSizeChange = false;

    @ConfigEntry.Category("Size Animation")
    public float totemSizeChangeSpeed = 1.0f;

    @ConfigEntry.Category("Size Animation")
    public float minTotemSize = 0.5f;

    @ConfigEntry.Category("Size Animation")
    public float maxTotemSize = 1.0f;

    @ConfigEntry.Category("Size Animation")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public boolean enableTotemPopSizeChange = false;

    @ConfigEntry.Category("Size Animation")
    public float totemPopSizeChangeSpeed = 1.0f;

    @ConfigEntry.Category("Size Animation")
    public float minTotemPopSize = 0.3f;

    @ConfigEntry.Category("Size Animation")
    public float maxTotemPopSize = 0.9f;

    public static Gui get() {
        return getConfigHolder(Gui.class).getConfig();
    }
}