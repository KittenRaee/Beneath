package com.eerussianguy.beneath.client.screen;

import com.eerussianguy.beneath.Beneath;
import com.eerussianguy.beneath.common.items.LostPageItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class LostPageScreen extends BeneathScreen
{
    private static final ResourceLocation TEXTURE = Beneath.identifier("textures/gui/lost_page.png");
    private static final Component NAME = Component.translatable("beneath.screen.lost_page");

    private final ItemStack targetStack;

    public LostPageScreen(ItemStack targetStack)
    {
        super(NAME);
        this.targetStack = targetStack;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick)
    {
        renderBackground(graphics);
        final int x = (width - 256) / 2;
        final int y = (height - 256) / 2 + 32;
        graphics.blit(TEXTURE, x, y, 0, 0, 256, 256);

        final ItemStack stack = targetStack;
        if (stack.getItem() instanceof LostPageItem item)
        {
            final Ingredient costIngredient = item.getCost(stack);
            final ItemStack reward = item.getReward(stack);

            final ClientLevel level = Minecraft.getInstance().level;
            if (level == null)
                return;
            final ItemStack[] items = costIngredient.getItems();
            final ItemStack cost = items[(int) (level.getGameTime() / 20) % items.length];

            drawCenteredLine(graphics, Component.translatable("beneath.screen.lost_page.cost"), x, y + 16);
            drawCenteredLine(graphics, Component.literal(item.getCostAmount(stack) + "x ").append(item.getSpecificIngredientTranslation(stack)), x, y + 26);
            graphics.renderItem(cost, x + 120, y + 34);

            drawCenteredLine(graphics, Component.translatable("beneath.screen.lost_page.reward"), x, y + 64);
            drawCenteredLine(graphics, Component.literal(item.getRewardAmount(stack) + "x ").append(reward.getHoverName()), x, y + 74);
            graphics.renderItem(reward, x + 120, y + 82);

            drawCenteredLine(graphics, Component.translatable("beneath.screen.lost_page.punishment"), x, y + 112);
            final MutableComponent pun = Beneath.translateEnum(item.getPunishment(stack));
            graphics.drawWordWrap(font, pun, x + 70, y + 122, 110, 0);
        }
    }


}
