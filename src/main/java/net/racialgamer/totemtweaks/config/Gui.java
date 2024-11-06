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

    @ConfigEntry.Category("Totem")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public boolean enableTotemSizeChange = false;

    @ConfigEntry.Category("Totem")
    public float totemSizeChangeSpeed = 1.0f;

    @ConfigEntry.Category("Totem")
    public float minTotemSize = 0.5f;

    @ConfigEntry.Category("Totem")
    public float maxTotemSize = 1.0f;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.PrefixText
    public boolean disableTotemPopAnimation = false;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 100)
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
    public int xPosition = 0;
    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.Tooltip
    public int yPosition = 0;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip
    public boolean enableTotemPopSizeChange = false;

    @ConfigEntry.Category("Totem Pop")
    public float totemPopSizeChangeSpeed = 1.0f;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 2)
    public float minTotemPopSize = 0.3f;

    @ConfigEntry.Category("Totem Pop")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 2)
    public float maxTotemPopSize = 0.9f;

    public static Gui get() {
        return getConfigHolder(Gui.class).getConfig();
    }
}