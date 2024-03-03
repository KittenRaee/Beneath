package com.eerussianguy.beneath.common.blocks;

import java.util.Locale;
import java.util.function.Supplier;
import com.eerussianguy.beneath.Beneath;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.dries007.tfc.world.feature.tree.TFCTreeGrower;

public enum Stem implements RegistryWood
{
    CRIMSON(true, MapColor.TERRACOTTA_RED, MapColor.COLOR_RED, 7, 8),
    WARPED(true, MapColor.TERRACOTTA_BLUE, MapColor.COLOR_BLUE, 7, 8);

    public static final Stem[] VALUES = values();

    private final String serializedName;
    private final boolean conifer;
    private final MapColor woodColor;
    private final MapColor barkColor;
    private final TFCTreeGrower tree;
    private final int maxDecayDistance;
    private final int daysToGrow;

    Stem(boolean conifer, MapColor woodColor, MapColor barkColor, int maxDecayDistance, int daysToGrow)
    {
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.conifer = conifer;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.tree = new TFCTreeGrower(Beneath.identifier("tree/" + serializedName), Beneath.identifier("tree/" + serializedName + "_large"));
        this.maxDecayDistance = maxDecayDistance;
        this.daysToGrow = daysToGrow;
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }

    public boolean isConifer()
    {
        return conifer;
    }

    @Override
    public MapColor woodColor()
    {
        return woodColor;
    }

    @Override
    public MapColor barkColor()
    {
        return barkColor;
    }

    @Override
    public TFCTreeGrower tree()
    {
        return tree;
    }

    @Override
    public int maxDecayDistance()
    {
        return maxDecayDistance;
    }

    @Override
    public int daysToGrow()
    {
        return defaultDaysToGrow();
    }

    public int defaultDaysToGrow()
    {
        return daysToGrow;
    }

    @Override
    public Supplier<Block> getBlock(Wood.BlockType type)
    {
        return BeneathBlocks.WOODS.get(this).get(type);
    }
}
