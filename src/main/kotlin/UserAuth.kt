class UserAuth{

    fun userAuth() {
        val usersMaps = mutableMapOf<String, String>() // Map to store users emails and passwords
        var userChoice: Int? = null

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
        usersMaps[email] = password
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