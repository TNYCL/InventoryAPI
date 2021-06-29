package com.craftrise.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.craftrise.inventory.PagedData;
import com.craftrise.inventory.PagedInventory;
import com.craftrise.util.ChatUtil;

public class InventoryListener implements Listener {

	@EventHandler
	public void handleAuctionMenu(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		PagedInventory inventory = PagedData.getData(player);
		ItemStack item = event.getCurrentItem();
		ItemMeta itemMeta = item.getItemMeta();
		if(itemMeta == null) return;
		String selectedItemName = ChatColor.stripColor(itemMeta.getDisplayName());
		if(inventory == null) return;
		if(!event.getInventory().getName().equals(inventory.getInventory().getName())) return;
		event.setCancelled(true);
		if(!event.getCurrentItem().getType().equals(Material.ARROW)) return;
		
		if(selectedItemName.equalsIgnoreCase("Önceki Sayfa")) {
			if(inventory.getPage() != 0) {
				inventory.setPage(inventory.getPage()-1);
				inventory.open();
				PagedData.updateData(player, inventory);
			} else {
				ChatUtil.message(player, "&cZaten ilk sayfadasın!");
			}
		} else if(selectedItemName.equalsIgnoreCase("Sonraki Sayfa")) {
			if(!inventory.isLastPage()) {
				inventory.setPage(inventory.getPage()+1);
				inventory.open();
				PagedData.updateData(player, inventory);
			} else {
				ChatUtil.message(player, "&cZaten son sayfadasın!");
			}
		}
	}
	
}
