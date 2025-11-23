package com.example.mycourutins.data

import androidx.core.os.unregisterForAllProfilingResults
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycourutins.domain.ShopItem
import com.example.mycourutins.domain.ShopListRepository
import kotlin.collections.sortedSetOf
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>({o1,o2 -> o1.id.compareTo(o2.id)})
    private val shopListLD = MutableLiveData<List<ShopItem>>()

    private var autoIncrementId = 0

    init {
        for (i in 0..1000) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopListItem(item)
        }
    }

    override fun addShopListItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        udpateList()
    }

    override fun deleteShopListItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        udpateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopListItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    fun udpateList() {
        shopListLD.value = shopList.toList()
    }
}