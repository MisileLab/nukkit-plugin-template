package xyz.misilelaboratory.example

import cn.nukkit.command.Command
import cn.nukkit.command.CommandSender
import cn.nukkit.plugin.PluginBase
import cn.nukkit.utils.Config
import cn.nukkit.utils.TextFormat
import cn.nukkit.utils.Utils
import java.io.File
import java.io.IOException
import java.util.*

class ExamplePlugin : PluginBase() {
    override fun onLoad() {
        logger.info(TextFormat.WHITE.toString() + "I've been loaded!")
    }

    override fun onEnable() {
        logger.info(TextFormat.DARK_GREEN.toString() + "I've been enabled!")
        logger.info(dataFolder.mkdirs().toString())

        //Register the EventListener
        server.pluginManager.registerEvents(EventListener(this), this)

        //PluginTask
        server.scheduler.scheduleRepeatingTask(BroadcastPluginTask(this), 200)

        //Save resources
        this.saveResource("string.txt")

        //Config reading and writing
        val config = Config(
                File(dataFolder, "config.yml"),
                Config.YAML,  //Default values (not necessary)
                object : LinkedHashMap<String?, Any?>() {
                    init {
                        put("this-is-a-key", "Hello! Config!")
                        put("another-key", true) //you can also put other standard objects!
                    }
                })
        //Now try to get the value, the default value will be given if the key isn't exist!
        logger.info(config.get("this-is-a-key", "this-is-default-value").toString())
        //Don't forget to save it!
        config.save()
    }

    override fun onDisable() {
        logger.info(TextFormat.DARK_RED.toString() + "I've been disabled!")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        when (command.name.lowercase(Locale.getDefault())) {
            "example" -> try {
                logger.info(Utils.readFile(File(dataFolder, "string.txt")) + " " + sender.name)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        return true
    }
}
