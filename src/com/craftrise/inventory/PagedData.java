package com.craftrise.inventory;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class PagedData {
	
	public static HashMap<Player, PagedInventory> data = new HashMap<>();
	
	public static PagedInventory getData(Player player) {
		return data.getOrDefault(player, null);
	}
	
	public static void addData(Player player, PagedInventory inventory) {
		data.put(player, inventory);
	}
	
	public static void updateData(Player player, PagedInventory inventory) {
		data.replace(player, inventory);
	}
	
	public static void deleteData(Player player) {
		if(getData(player) == null) {
			return;
		}
		data.remove(player);
	}

}
