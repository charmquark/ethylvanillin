package com.invironz.ethylvanillin.common.item.base;

import com.invironz.ethylvanillin.Ethylvanillin;
import com.invironz.ethylvanillin.lib.EVNames;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by charmquark on 6/9/2016.
 */
public class EVItem extends Item {

    public EVItem(String name) {
        super();
        setName(name);
    }

    public EVItem(String name, CreativeTabs creativeTab) {
        this(name);
        setCreativeTab(creativeTab);
    }


    public void registerItemModel() {
        Ethylvanillin.proxy.registerItemRenderer(this, 0);
    }


    private void setName(String name) {
        setUnlocalizedName(EVNames.PREFIX + name);
        setRegistryName(name);
    }
}
