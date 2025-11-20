package com.example.mycourutins.presentation

import androidx.lifecycle.ViewModel
import com.example.mycourutins.data.ShopListRepositoryImpl
import com.example.mycourutins.domain.DeleteShopListItemUseCase
import com.example.mycourutins.domain.EditShopItemUseCase
import com.example.mycourutins.domain.GetShopListUseCase
import com.example.mycourutins.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopListItemUseCase = DeleteShopListItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        getShopList()
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopListItemUseCase.deleteShopListItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

}