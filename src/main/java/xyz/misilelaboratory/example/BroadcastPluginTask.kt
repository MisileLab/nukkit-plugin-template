package xyz.misilelaboratory.example

import cn.nukkit.scheduler.PluginTask

class BroadcastPluginTask(owner: ExamplePlugin?) : PluginTask<ExamplePlugin?>(owner) {
    override fun onRun(currentTick: Int) {
        getOwner()!!.logger.info("I've run on tick $currentTick")
    }
}
