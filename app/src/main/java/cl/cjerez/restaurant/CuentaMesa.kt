package cl.cjerez.restaurant

class CuentaMesa (
    var aceptaPropina: Boolean = true,
    val _items: MutableList<ItemMesa> = mutableListOf()
) {

    fun agregarItem(imenu: ItemMenu, cantidad: Int) {
        val imesa = ItemMesa(cantidad, imenu)
        val index = _items.indexOfFirst { it.itemMenu.nombre == imesa.itemMenu.nombre }
        if( index != -1 ){
            _items[index] = imesa
        }else {
            _items.add(imesa)
        }
    }

    fun agregarItem(imesa: ItemMesa) {
        val index = _items.indexOfFirst { it.itemMenu.nombre == imesa.itemMenu.nombre }
        if( index != -1 ){
            _items[index] = imesa
        }else {
            _items.add(imesa)
        }
    }

    fun calcularTotalSinPropina(): Int {
        var montoBruto = 0
        for (mesa in _items) {
            montoBruto += mesa.calcularSubtotal()
        }
        return montoBruto
    }

    fun calcularPropina(): Int {
        val montoBruto = calcularTotalSinPropina()
        return (montoBruto * 0.1).toInt()
    }

    fun calcularTotalConPropina(): Int {
        val montoBruto = calcularTotalSinPropina()
        val montoPropina = calcularPropina()

        if(aceptaPropina == true){
            return montoBruto + montoPropina
        } else {
            return montoBruto
        }
    }
}