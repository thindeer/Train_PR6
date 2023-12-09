import kotlin.random.Random

data class Train(val direction: String, val wagons: List<Wagon>)

data class Wagon(val capacity: Int)

fun main() {
    val cities = listOf("Бийск", "Барнаул", "Новосибирск", "Омск", "Красноярск", "Иркутск", "Улан-Удэ", "Чита", "Хабаровск", "Владивосток", "Краснодар", "Сочи", "Ростов-на-Дону", "Воронеж", "Москва")

    var exitFlag = false
    var currentDirection: String? = null
    var passengersCount: Int = 0
    var train: Train? = null

    while (!exitFlag) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("EXIT. Завершить программу")

        val choice = readLine()

        when (choice) {
            "1" -> currentDirection = createDirection(cities)
            "2" -> passengersCount = sellTickets()
            "3" -> train = createTrain(passengersCount)
            "4" -> sendTrain(train, currentDirection)
            "EXIT" -> exitFlag = true
            else -> println("Неправильный выбор. Попробуйте еще раз.")
        }
    }
}

fun createDirection(cities: List<String>): String {
    val startCity = cities.random()
    var endCity = cities.random()

    while (endCity == startCity) {
        endCity = cities.random()
    }

    val direction = "$startCity - $endCity"
    println("Направление создано: $direction")
    return direction
}

fun sellTickets(): Int {
    val passengers = Random.nextInt(5, 202)
    println("Продано билетов: $passengers")
    return passengers
}

fun createTrain(passengersCount: Int): Train {
    val wagons = mutableListOf<Wagon>()
    var remainingPassengers = passengersCount

    while (remainingPassengers > 0) {
        val wagonCapacity = Random.nextInt(5, 26)
        val passengersInWagon = if (remainingPassengers >= wagonCapacity) wagonCapacity else remainingPassengers
        wagons.add(Wagon(passengersInWagon))
        remainingPassengers -= passengersInWagon
    }

    val train = Train("Направление не указано", wagons)
    println("Поезд сформирован")
    return train
}

fun sendTrain(train: Train?, direction: String?) {
    if (train != null && direction != null) {
        println("Поезд $direction, состоящий из ${train.wagons.size} вагонов отправлен.")
        for ((index, wagon) in train.wagons.withIndex()) {
            println("Вагон ${index + 1}: Вместимость - ${wagon.capacity}, Пассажиров - ${wagon.capacity}")
        }
    } else {
        println("Ошибка: Направление или поезд не созданы.")
    }
}





