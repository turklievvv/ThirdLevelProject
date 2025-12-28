package com.example.mycourutins.domain

class DeleteShopListItemUseCase(private val shopListRepository: ShopListRepository) {


    suspend fun deleteShopListItem(shopItem: ShopItem) {
        shopListRepository.deleteShopListItem(shopItem)
    }
}