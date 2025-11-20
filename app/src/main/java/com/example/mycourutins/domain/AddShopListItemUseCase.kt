package com.example.mycourutins.domain

class AddShopListItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopListItem(shopItem: ShopItem){
        shopListRepository.addShopListItem(shopItem)
    }
}