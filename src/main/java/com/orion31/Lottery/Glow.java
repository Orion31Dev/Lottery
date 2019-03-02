package com.orion31.Lottery;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

/*
 * This is an empty enchantment to give glow effect to non enchanted items,
 * such as tickets.
 */
public class Glow extends Enchantment {

    public Glow() {
	super(70);
    }

    @Override
    public boolean canEnchantItem(ItemStack arg0) {
	return false;
    }

    @Override
    public boolean conflictsWith(Enchantment arg0) {
	return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
	return null;
    }

    @Override
    public int getMaxLevel() {
	return 0;
    }

    @Override
    public String getName() {
	return null;
    }

    @Override
    public int getStartLevel() {
	return 0;
    }

    @Override
    public boolean isTreasure() {
	return false;
    }

    @Override
    public boolean isCursed() {
	return false;
    }

}