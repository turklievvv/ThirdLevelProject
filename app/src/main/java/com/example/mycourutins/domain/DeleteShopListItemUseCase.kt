package com.example.mycourutins.domain

class DeleteShopListItemUseCase(private val shopListRepository: ShopListRepository) {


    fun deleteShopListItem(shopItem: ShopItem) {
        shopListRepository.deleteShopListItem(shopItem)
    }
}