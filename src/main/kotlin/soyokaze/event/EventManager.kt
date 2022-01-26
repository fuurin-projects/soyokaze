package soyokaze.event

class EventManager {

    val eventList = mutableMapOf<String, Event>()

    var eventGlobalList: Map<String, Event> = mutableMapOf()

    fun postInit(eventList: Map<String, Event>) {
        this.eventList += eventList

        eventGlobalList = this.eventList.filter { entry ->
            entry.key.startsWith("global.")
        }

        console.log("size=${eventGlobalList.size}")


    }

}