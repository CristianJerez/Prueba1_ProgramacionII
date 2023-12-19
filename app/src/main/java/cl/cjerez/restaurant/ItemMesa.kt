package cl.cjerez.restaurant

class ItemMesa (val cantidad: Int, val itemMenu:ItemMenu){

    fun calcularSubtotal():Int{
        return cantidad * itemMenu.precio.toInt()
    }
}