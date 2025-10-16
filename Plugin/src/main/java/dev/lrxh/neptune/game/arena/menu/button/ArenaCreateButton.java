package dev.lrxh.neptune.game.arena.menu.button;

import dev.lrxh.neptune.API;
import dev.lrxh.neptune.Neptune;
import dev.lrxh.neptune.game.arena.procedure.ArenaProcedureType;
import dev.lrxh.neptune.profile.impl.Profile;
import dev.lrxh.neptune.utils.CC;
import dev.lrxh.neptune.utils.ItemBuilder;
import dev.lrxh.neptune.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ArenaCreateButton extends Button {

    public ArenaCreateButton(int slot) {
        super(slot, false);
    }

    @Override
    public void onClick(ClickType type, Player player) {
        World world = Neptune.get().getCache().getSpawn().getWorld();
        if (world == null) {
            player.sendMessage(CC.error("Spawn world is not set!, use /neptune setspawn"));
            return;
        }

        if (player.getWorld().equals(world)) {
            player.sendMessage(CC.error("You cannot create an arena in the same world as the spawn!"));
            return;
        }

        Profile profile = API.getProfile(player);
        profile.getArenaProcedure().setType(ArenaProcedureType.CREATE);
        player.closeInventory();
        player.sendMessage(CC.info("&ePlease type the arena name"));
    }

    @Override
    public ItemStack getItemStack(Player player) {
        return new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).name("&aCreate arena").build();
    }
}
