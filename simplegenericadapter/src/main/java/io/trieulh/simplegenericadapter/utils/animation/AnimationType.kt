package io.trieulh.simplegenericadapter.utils.animation

import androidx.annotation.AnimRes
import io.trieulh.simplegenericadapter.R

/**
 * Created by Trieulh on 08,August,2019
 */
enum class AnimationType(@AnimRes val value: Int) {
    ALPHA_IN(R.anim.alpha_in),
    SCALE_IN(R.anim.scale_in),
    SLIDE_IN_BOTTOM(R.anim.slide_in_bottom),
    SLIDE_IN_LEFT(R.anim.slide_in_left),
    SLIDE_IN_RIGHT(R.anim.slide_in_right),
}