
import com.google.gson.Gson
import java.io.File

class UserAuth{

    private val gson = Gson()
    private val file = File("users_data.json")

    // Load existing users from file
    private fun loadUsers(): MutableMap<String, String> {
        if (file.exists()) {
            val json = file.readText() // Read JSON string from file
            return gson.fromJson(json, MutableMap::class.java) as MutableMap<String, String>
        }
        return mutableMapOf() // Return an empty map if no data exists (or is the first user)
    }

    // Save users to file
    private fun saveUsers(usersMaps: MutableMap<String, String>) {
        val json = gson.toJson(usersMaps) // Convert map to JSON
        file.writeText(json) // Save JSON string to file
    }

    fun userAuth() {

        var userChoice: Int? = null
        var usersMaps = loadUsers() // Load users from file at the start

        do {
            println("Por favor, elija una opción:")
            println("1 - Login")
            println("2 - Registro")
            println("3 - Finalizar")

            val input = readln()
            userChoice = input.toIntOrNull() // convert input to Integer

            when (userChoice) {
                1 -> checkLog(usersMaps)
                2 -> registerUser(usersMaps)
                3 -> println("Programa Finalizado.")
                else -> println("Opción inválida, intente de nuevo.")
            }
        } while (userChoice != 3)
    }

    fun registerUser(usersMaps: MutableMap<String, String>) {
        println("Ingrese su Mail:")
        val email = readln()
        println("Ingrese su Contraseña:")
        val password = readln()
        usersMaps[email] = password //Store new user
        saveUsers(usersMaps) // Save the updated map to file
        println("Usuario registrado con éxito.")
    }

    fun checkLog(usersMaps: MutableMap<String, String>) {
        println("Ingrese su Mail:")
        val email = readln()
        println("Ingrese su Contraseña:")
        val password = readln()
        // check data
        if (usersMaps.containsKey(email)) {
            if (usersMaps[email] == password) {
                println("Se inició sesión correctamente.")
            } else {
                println("Contraseña incorrecta.")
            }
        } else {
            println("Mail incorrecto.")
        }
    }
}