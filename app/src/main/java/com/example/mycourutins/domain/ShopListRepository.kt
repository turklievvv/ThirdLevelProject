package com.example.mycourutins.domain

interface ShopListRepository {

    fun addShopListItem(shopItem: ShopItem)

    fun deleteShopListItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId:Int): ShopItem

    fun getShopList(): List<ShopItem>
}
