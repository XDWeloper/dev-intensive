package ru.skillbranch.skillarticles.ui.custom.behaviors

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat
import ru.skillbranch.skillarticles.ui.RootActivity
import ru.skillbranch.skillarticles.ui.custom.ArticleSubmenu
import ru.skillbranch.skillarticles.viewmodels.ArticleViewModel

class SubmenuBehavior : CoordinatorLayout.Behavior<ArticleSubmenu>() {

    private var viewModel : ArticleViewModel? = null


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ArticleSubmenu,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {

        if(viewModel == null) {
            val rootActivity = coordinatorLayout.context as RootActivity
            viewModel = rootActivity.getViewModel()
        }

        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ArticleSubmenu,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {

//        if(dy > 0 && viewModel!!.currentState.isShowMenu){
//            child.close()
//        }
//        if(dy < 0 && viewModel!!.currentState.isShowMenu){
//            child.open()
//        }

        if(child.isOpen){
            val offset = MathUtils.clamp(child.translationX + dy * 4 , 0f, child.width.toFloat())
            if (offset != child.translationX) child.translationX = offset
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)


//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}