package events

data class NetworkEvent(val something: String) : Event() {

    override val message: String
        get() = something

}