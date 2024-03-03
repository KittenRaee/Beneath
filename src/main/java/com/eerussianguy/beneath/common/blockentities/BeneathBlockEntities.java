package com.eerussianguy.beneath.common.blockentities;

import java.util.function.Supplier;
import java.util.stream.Stream;
import com.eerussianguy.beneath.common.blocks.BeneathBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistrationHelpers;

import static com.eerussianguy.beneath.Beneath.*;

public class BeneathBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<BlockEntityType<SoulFarmlandBlockEntity>> SOUL_FARMLAND = register("soul_farmland", SoulFarmlandBlockEntity::new, BeneathBlocks.SOUL_FARMLAND);
    public static final RegistryObject<BlockEntityType<NetherCropBlockEntity>> NETHER_CROP = register("nether_crop", NetherCropBlockEntity::new, BeneathBlocks.CROPS.values().stream());
    public static final RegistryObject<BlockEntityType<HellforgeBlockEntity>> HELLFORGE = register("hellforge", HellforgeBlockEntity::new, BeneathBlocks.HELLFORGE);
    public static final RegistryObject<BlockEntityType<BeneathTickCounterBlockEntity>> TICK_COUNTER = register("tick_counter", BeneathTickCounterBlockEntity::new, Stream.of());
    public static final RegistryObject<BlockEntityType<BeneathSignBlockEntity>> SIGN = register("sign", BeneathSignBlockEntity::new, BeneathBlocks.WOODS.values().stream().flatMap(map -> Stream.of(Wood.BlockType.SIGN, Wood.BlockType.WALL_SIGN).map(map::get)));

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block)
    {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Stream<? extends Supplier<? extends Block>> blocks)
    {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks);
    }

}
