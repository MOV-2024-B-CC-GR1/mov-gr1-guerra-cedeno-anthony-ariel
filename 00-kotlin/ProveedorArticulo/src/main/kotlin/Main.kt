import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

// Main function
fun main() {
    val crud = CRUD()
    while (true) {
        println("\nMenú de opciones:")
        println("1. Crear Proveedor")
        println("2. Listar Proveedores")
        println("3. Actualizar Proveedor")
        println("4. Eliminar Proveedor")
        println("5. Agregar Artículo a Proveedor")
        println("6. Listar Artículos de un Proveedor")
        println("7. Actualizar Artículo de un Proveedor")
        println("8. Eliminar Artículo de un Proveedor")
        println("9. Salir")

        print("Seleccione una opción: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Ingrese ID del proveedor: ")
                val id = readLine()?.toIntOrNull() ?: return
                print("Ingrese nombre del proveedor: ")
                val nombre = readLine().orEmpty()
                print("Ingrese teléfono del proveedor: ")
                val telefono = readLine().orEmpty()
                print("Ingrese fecha de registro del proveedor (YYYY-MM-DD): ")
                val fechaRegistro = readLine().orEmpty()
                print("¿Está activo? (true/false): ")
                val activo = readLine()?.toBoolean() ?: false
                crud.crearProveedor(Proveedor(id, nombre, telefono, fechaRegistro, activo))
                println("Proveedor creado con éxito.")
            }
            2 -> {
                println("Lista de proveedores:")
                crud.leerProveedores().forEach { println(it) }
            }
            3 -> {
                print("Ingrese ID del proveedor a actualizar: ")
                val id = readLine()?.toIntOrNull() ?: return
                print("Nuevo nombre (o Enter para no cambiar): ")
                val nombre = readLine().takeIf { it?.isNotEmpty() == true }
                print("Nuevo teléfono (o Enter para no cambiar): ")
                val telefono = readLine().takeIf { it?.isNotEmpty() == true }
                print("Nueva fecha de registro (o Enter para no cambiar): ")
                val fechaRegistro = readLine().takeIf { it?.isNotEmpty() == true }
                print("¿Está activo? (true/false o Enter para no cambiar): ")
                val activo = readLine()?.toBoolean()
                crud.actualizarProveedor(id, nombre, telefono, fechaRegistro, activo)
                println("Proveedor actualizado con éxito.")
            }
            4 -> {
                print("Ingrese ID del proveedor a eliminar: ")
                val id = readLine()?.toIntOrNull() ?: return
                crud.eliminarProveedor(id)
                println("Proveedor eliminado con éxito.")
            }
            5 -> {
                print("Ingrese ID del proveedor: ")
                val idProveedor = readLine()?.toIntOrNull() ?: return
                print("Ingrese ID del artículo: ")
                val idArticulo = readLine()?.toIntOrNull() ?: return
                print("Ingrese nombre del artículo: ")
                val nombre = readLine().orEmpty()
                print("Ingrese descripción del artículo: ")
                val descripcion = readLine().orEmpty()
                print("Ingrese precio del artículo: ")
                val precio = readLine()?.toDoubleOrNull() ?: return
                print("Ingrese cantidad del artículo: ")
                val cantidad = readLine()?.toIntOrNull() ?: return
                crud.agregarArticulo(idProveedor, Articulo(idArticulo, nombre, descripcion, precio, cantidad))
                println("Artículo agregado con éxito.")
            }
            6 -> {
                print("Ingrese ID del proveedor: ")
                val idProveedor = readLine()?.toIntOrNull() ?: return
                println("Lista de artículos:")
                crud.leerArticulos(idProveedor)?.forEach { println(it) } ?: println("Proveedor no encontrado.")
            }
            7 -> {
                print("Ingrese ID del proveedor: ")
                val idProveedor = readLine()?.toIntOrNull() ?: return
                print("Ingrese ID del artículo a actualizar: ")
                val idArticulo = readLine()?.toIntOrNull() ?: return
                print("Nuevo nombre (o Enter para no cambiar): ")
                val nombre = readLine().takeIf { it?.isNotEmpty() == true }
                print("Nueva descripción (o Enter para no cambiar): ")
                val descripcion = readLine().takeIf { it?.isNotEmpty() == true }
                print("Nuevo precio (o Enter para no cambiar): ")
                val precio = readLine()?.toDoubleOrNull()
                print("Nueva cantidad (o Enter para no cambiar): ")
                val cantidad = readLine()?.toIntOrNull()
                crud.actualizarArticulo(idProveedor, idArticulo, nombre, descripcion, precio, cantidad)
                println("Artículo actualizado con éxito.")
            }
            8 -> {
                print("Ingrese ID del proveedor: ")
                val idProveedor = readLine()?.toIntOrNull() ?: return
                print("Ingrese ID del artículo a eliminar: ")
                val idArticulo = readLine()?.toIntOrNull() ?: return
                crud.eliminarArticulo(idProveedor, idArticulo)
                println("Artículo eliminado con éxito.")
            }
            9 -> {
                println("Saliendo del programa...")
                break
            }
            else -> println("Opción no válida, intente nuevamente.")
        }
    }
}

/* Modelo de datos
* Proveedor(1) : Articulo (N)
*/
data class Proveedor(
    val id: Int,
    var nombre: String,
    var telefono: String,
    var fechaRegistro: String,
    var activo: Boolean,
    val articulos: MutableList<Articulo> = mutableListOf()
)

data class Articulo(
    val id: Int,
    var nombre: String,
    var descripcion: String,
    var precio: Double,
    var cantidad: Int
)

// Clase para manejar el CRUD
class CRUD {
    private val gson = Gson()
    private val archivoProveedores = File("proveedores.json")

    private var proveedores: MutableList<Proveedor> = mutableListOf()

    init {
        cargarDatos()
    }

    // Cargar datos desde el archivo JSON
    private fun cargarDatos() {
        if (archivoProveedores.exists()) {
            val json = archivoProveedores.readText()
            val tipoLista = object : TypeToken<MutableList<Proveedor>>() {}.type
            proveedores = gson.fromJson(json, tipoLista) ?: mutableListOf()
        }
    }

    // Guardar datos en el archivo JSON
    private fun guardarDatos() {
        val json = gson.toJson(proveedores)
        archivoProveedores.writeText(json)
    }

    // CRUD para Proveedor
    fun crearProveedor(proveedor: Proveedor) {
        proveedores.add(proveedor)
        guardarDatos()
    }

    fun leerProveedores(): List<Proveedor> = proveedores

    fun actualizarProveedor(id: Int, nombre: String?, telefono: String?, fechaRegistro: String?, activo: Boolean?) {
        val proveedor = proveedores.find { it.id == id }
        if (proveedor != null) {
            nombre?.let { proveedor.nombre = it }
            telefono?.let { proveedor.telefono = it }
            fechaRegistro?.let { proveedor.fechaRegistro = it }
            activo?.let { proveedor.activo = it }
            guardarDatos()
        }
    }

    fun eliminarProveedor(id: Int) {
        proveedores.removeIf { it.id == id }
        guardarDatos()
    }

    // CRUD para Articulo
    fun agregarArticulo(idProveedor: Int, articulo: Articulo) {
        val proveedor = proveedores.find { it.id == idProveedor }
        proveedor?.let {
            it.articulos.add(articulo)
            guardarDatos()
        }
    }

    fun leerArticulos(idProveedor: Int): List<Articulo>? {
        return proveedores.find { it.id == idProveedor }?.articulos
    }

    fun actualizarArticulo(idProveedor: Int, idArticulo: Int, nombre: String?, descripcion: String?, precio: Double?, cantidad: Int?) {
        val proveedor = proveedores.find { it.id == idProveedor }
        val articulo = proveedor?.articulos?.find { it.id == idArticulo }
        if (articulo != null) {
            nombre?.let { articulo.nombre = it }
            descripcion?.let { articulo.descripcion = it }
            precio?.let { articulo.precio = it }
            cantidad?.let { articulo.cantidad = it }
            guardarDatos()
        }
    }

    fun eliminarArticulo(idProveedor: Int, idArticulo: Int) {
        val proveedor = proveedores.find { it.id == idProveedor }
        proveedor?.let {
            it.articulos.removeIf { articulo -> articulo.id == idArticulo }
            guardarDatos()
        }
    }
}
