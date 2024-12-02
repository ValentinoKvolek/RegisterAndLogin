import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.callloging.*
import org.mindrot.jbcrypt.BCrypt

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    val userAuth = UserAuth() // intance my logic class
    install(CallLogging)
    routing {
        post("/login") {
            val parameters = call.receiveParameters()
            val email = parameters["email"]
            val password = parameters["password"]

            if (email != null && password != null) {

                val usersMaps = userAuth.loadUsers() //load my register users
                // check data
                if (usersMaps.containsKey(email)) {
                    val storedHashedPassword = usersMaps[email]!!
                    // Check if the entered password matches the stored hashed password
                    if (BCrypt.checkpw(password, storedHashedPassword)) {
                        println("Se inició sesión correctamente.")
                    } else {
                        println("Contraseña incorrecta.")
                    }
                } else {
                    println("Mail incorrecto.")
                }
            }
        }
        post("/register") {
            val parameters = call.receiveParameters()
            val email = parameters["email"]
            val password = parameters["password"]

            if (email != null && password != null) {
                if (userAuth.isValidEmail(email)) {

                    val usersMaps = userAuth.loadUsers() //load my register users

                    // check is the input email is already exist
                    if (usersMaps.containsKey(email)) {
                        call.respondText("El usuario ya existe", status = HttpStatusCode.Conflict)
                    } else {
                        // register new user
                        userAuth.registerUser(usersMaps, email, password)
                        call.respondText("Usuario registrado exitosamente", status = HttpStatusCode.OK)
                    }
                } else {
                    call.respondText("Email inválido", status = HttpStatusCode.BadRequest)
                }
            } else {
                call.respondText("Faltan datos", status = HttpStatusCode.BadRequest)
            }
        }
    }
}
