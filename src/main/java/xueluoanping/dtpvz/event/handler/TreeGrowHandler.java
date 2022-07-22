package xueluoanping.dtpvz.event.handler;

import com.ferreusveritas.dynamictrees.event.SpeciesPostGenerationEvent;
import com.hungteen.pvz.common.block.BlockRegister;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

import static xueluoanping.dtpvz.DTPVZ.LOGGER;
public class TreeGrowHandler {
    public static final TreeGrowHandler instance = new TreeGrowHandler();
    @SubscribeEvent
    public void onTreeGrowEvent(SpeciesPostGenerationEvent event) {
        Random random =event.getWorld().getRandom();
        if (random.nextInt(16)==0)
        {
//            LOGGER.info(""+event);
            event.getWorld().setBlock(event.getRootPos(),BlockRegister.ORIGIN_ORE.get().defaultBlockState(),0);
        }
    }
}
