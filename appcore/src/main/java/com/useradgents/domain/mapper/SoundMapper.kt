package com.useradgents.domain.mapper

import com.useradgents.domain.model.Sound
import com.useradgents.domain.viewobject.SoundVO

interface SoundMapper {
    fun map(input: Sound): SoundVO
}