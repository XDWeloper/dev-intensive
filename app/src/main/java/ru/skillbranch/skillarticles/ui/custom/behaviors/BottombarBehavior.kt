package ru.skillbranch.skillarticles.ui.custom.behaviors

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat
import ru.skillbranch.skillarticles.ui.RootActivity
import ru.skillbranch.skillarticles.ui.custom.ArticleSubmenu
import ru.skillbranch.skillarticles.ui.custom.Bottombar
import ru.skillbranch.skillarticles.viewmodels.ArticleViewModel

class BottombarBehavior : CoordinatorLayout.Behavior<Bottombar>() {

    private var articleSubMenu : ArticleSubmenu? = null
    private var viewModel : ArticleViewModel? = null


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Bottombar,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        if(articleSubMenu == null)
            articleSubMenu = if (coordinatorLayout.childCount > 2) coordinatorLayout.getChildAt(2) as ArticleSubmenu else null
        if(viewModel == null) {
            val rootActivity = coordinatorLayout.context as RootActivity
            viewModel = rootActivity.getViewModel()
        }

        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Bottombar,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {

//        if(articleSubMenu!= null && dy > 0 && viewModel!!.currentState.isShowMenu){
//            articleSubMenu!!.close()
//        }
        if(articleSubMenu!= null && dy < 0 && viewModel!!.currentState.isShowMenu){
            articleSubMenu!!.open()
        }

//        val offset = MathUtils.clamp(child.translationY.plus(dy) , -0f, child.minHeight.toFloat())
//        if (offset != child.translationY) child.translationY = offset

        child.translationY = MathUtils.clamp(child.translationY.plus(dy) , 0f, child.minHeight.toFloat())
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}