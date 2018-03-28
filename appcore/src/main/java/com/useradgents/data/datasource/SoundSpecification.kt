package com.useradgents.data.datasource

class SoundSpecification : Specification {
    data class ByRef(val id: String) : Specification
    class All : Specification
}