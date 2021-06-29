package com.craftrise.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.craftrise.util.ChatUtil;

public class PagedInventory {
	
	private Player player;
	private int page;
	private int size;
	private int max_item_per_page;
	private Inventory inventory;
	private List<ItemStack> items;
	private List<PagedItem> customitem = new ArrayList<>();

	public PagedInventory(Player player, int size, String name, List<ItemStack> items) {
		this.player = player;
		this.inventory = Bukkit.createInventory(player, size, name);
		this.size = size;
		this.max_item_per_page = size-9;
		this.items = items;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public int getPage() {
		return this.page;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public List<ItemStack> getItems() {
		return this.items;
	}
	
	public int getMaxItemPerPage() {
		return this.max_item_per_page;
	}
	
	public List<PagedItem> getCustomItem() {
		return this.customitem;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void addCustomItem(PagedItem customitem) {
		this.customitem.add(customitem);
	}
	
	public boolean isLastPage() {
		int real_item_size = this.items.size()-1;
		if((int)real_item_size/max_item_per_page == page) {
			return true;
		}
		return false;
	}
	
	public void setPageItem() {
		if(!isLastPage()) {
			ItemStack next = new ItemStack(Material.ARROW);
			ItemMeta nextMeta = next.getItemMeta();
			nextMeta.setDisplayName(ChatUtil.color("&eSonraki Sayfa"));
			next.setItemMeta(nextMeta);
			inventory.setItem(this.size-4, next);
		}
		if(page != 0) {
			ItemStack back = new ItemStack(Material.ARROW);
			ItemMeta backMeta = back.getItemMeta();
			backMeta.setDisplayName(ChatUtil.color("&eÖnceki Sayfa"));
			back.setItemMeta(backMeta);
			inventory.setItem(this.size-6, back);
		}
		ItemStack currentPage = new ItemStack(Material.PAPER);
		ItemMeta currentPageMeta = currentPage.getItemMeta();
		int real_page = page+1;
		currentPageMeta.setDisplayName(ChatUtil.color("&a"+real_page+". Sayfa"));
		currentPage.setItemMeta(currentPageMeta);
		inventory.setItem(this.size-5, currentPage);
		if(!this.customitem.isEmpty()) {
			for(int i=0;i<this.customitem.size();i++) {
				PagedItem itemdata = this.customitem.get(i);
				inventory.setItem(itemdata.getLocation(), itemdata.getItem());
			}
		}
	}
	
	public void open() {
		inventory.clear();
		setPageItem();
		if(page == 0) {
			if(max_item_per_page > this.items.size()) {
				for(int i=page*max_item_per_page;i<this.items.size();i++) {
					inventory.setItem(i-page*max_item_per_page, this.items.get(i));
				}
			} else {
				for(int i=page*max_item_per_page;i<max_item_per_page;i++) {
					inventory.setItem(i-page*max_item_per_page, this.items.get(i));
				}
			}
		} else if(page > 0) {
			int real_page = page+1;
			if(max_item_per_page*real_page> this.items.size()) {
				for(int i=page*max_item_per_page;i<this.items.size();i++) {
					inventory.setItem(i-page*max_item_per_page, this.items.get(i));
				}
			} else {
				for(int i=page*max_item_per_page;i<max_item_per_page*real_page;i++) {
					inventory.setItem(i-page*max_item_per_page, this.items.get(i));
				}
			}
		}
		player.openInventory(this.inventory);
	}
	
}
