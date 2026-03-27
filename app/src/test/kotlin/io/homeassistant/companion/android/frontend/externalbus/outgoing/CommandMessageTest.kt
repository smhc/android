package io.homeassistant.companion.android.frontend.externalbus.outgoing

import io.homeassistant.companion.android.frontend.externalbus.frontendExternalBusJson
import io.homeassistant.companion.android.testing.unit.ConsoleLogExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(ConsoleLogExtension::class)
class CommandMessageTest {

    @Test
    fun `Given NavigateTo with replace when serializing then produces correct JSON`() {
        val message = NavigateTo(path = "/", replace = true)

        val json = frontendExternalBusJson.encodeToString<OutgoingExternalBusMessage>(message)

        assertEquals(
            """{"type":"command","id":null,"command":"navigate","payload":{"path":"/","options":{"replace":true}}}""",
            json,
        )
    }

    @Test
    fun `Given NavigateTo without replace when serializing then defaults replace to false`() {
        val message = NavigateTo(path = "/lovelace/dashboard")

        val json = frontendExternalBusJson.encodeToString<OutgoingExternalBusMessage>(message)

        assertEquals(
            """{"type":"command","id":null,"command":"navigate","payload":{"path":"/lovelace/dashboard","options":{"replace":false}}}""",
            json,
        )
    }

    @Test
    fun `Given ShowSidebar when serializing then produces correct JSON without payload`() {
        val json = frontendExternalBusJson.encodeToString<OutgoingExternalBusMessage>(ShowSidebar)

        assertEquals(
            """{"type":"command","id":null,"command":"sidebar/show","payload":null}""",
            json,
        )
    }
}
