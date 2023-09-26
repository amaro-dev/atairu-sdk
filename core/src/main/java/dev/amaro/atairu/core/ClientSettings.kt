package dev.amaro.atairu.core

class ClientSettings(
    val environment: Environment = Environment.Production
)

sealed class Environment(val hostName: String) {
    object Production : Environment("atairu.vercel.app")
    object Development : Environment("atairu-dev.vercel.app")
}