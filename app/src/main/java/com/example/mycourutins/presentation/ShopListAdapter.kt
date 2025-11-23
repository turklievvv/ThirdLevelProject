package com.example.mycourutins.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mycourutins.R
import com.example.mycourutins.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHodler>(
    ShopItemDiffCallback()
) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShopItemViewHodler {

        val layout = when (viewType) {
            ITEM_SHOP_ENABLED -> R.layout.item_shop_enabled
            ITEM_SHOP_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ShopItemViewHodler(view)
    }

    override fun onBindViewHolder(
        holder: ShopItemViewHodler,
        position: Int
    ) {
        val currentItem = getItem(position)
        holder.tvName.text = currentItem.name
        holder.tvCount.text = currentItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(currentItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(currentItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val itemShop = getItem(position)
        return (if (itemShop.enabled) {
            ITEM_SHOP_ENABLED
        } else {
            ITEM_SHOP_DISABLED
        })
    }

    companion object {
        const val ITEM_SHOP_ENABLED = 1
        const val ITEM_SHOP_DISABLED = 0
        const val MAX_POOL_SIZE = 15
    }

}
