package com.useradgents.domain.mapper

import com.useradgents.domain.model.Sound
import com.useradgents.domain.viewobject.SoundVO
import javax.inject.Inject

class SoundMapperImpl @Inject constructor(): SoundMapper {
    override fun map(input: Sound): SoundVO {
        return SoundVO(
                input.character,
                input.episode,
                input.file,
                input.title)
    }
}