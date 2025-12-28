package com.example.mycourutins.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycourutins.data.ShopListRepositoryImpl
import com.example.mycourutins.domain.DeleteShopListItemUseCase
import com.example.mycourutins.domain.EditShopItemUseCase
import com.example.mycourutins.domain.GetShopListUseCase
import com.example.mycourutins.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopListItemUseCase = DeleteShopListItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()
    fun deleteShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            deleteShopListItemUseCase.deleteShopListItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}