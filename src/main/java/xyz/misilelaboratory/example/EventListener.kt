package xyz.misilelaboratory.example

import cn.nukkit.event.EventHandler
import cn.nukkit.event.EventPriority
import cn.nukkit.event.Listener
import cn.nukkit.event.server.ServerCommandEvent

class EventListener(private val plugin: ExamplePlugin) : Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false) //DON'T FORGET THE ANNOTATION @EventHandler
    fun onServerCommand(event: ServerCommandEvent?) {
        plugin.logger.info("ServerCommandEvent is called!")
        //you can do more here!
    }
}
