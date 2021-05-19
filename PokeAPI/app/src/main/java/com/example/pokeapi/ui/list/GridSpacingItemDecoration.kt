package com.example.pokeapi.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = spacing;
        outRect.right = spacing;
        outRect.bottom = spacing;

        if ((parent.getChildLayoutPosition(view) == 0) || (parent.getChildLayoutPosition(view) == 1)) {
            outRect.top = spacing + spacing / 2;
        } else {
            outRect.top = 0;
        }
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.right = spacing / 2;
        }

        if (parent.getChildLayoutPosition(view) % 2 == 1) {
            outRect.left = spacing / 2;
        }
    }
}