package com.useradgents.data.datasource

class BurgerSpecification : Specification {
    data class ByRef(val id: String) : Specification
    class All : Specification
}