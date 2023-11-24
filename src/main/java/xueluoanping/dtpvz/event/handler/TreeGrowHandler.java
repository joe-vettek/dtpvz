package xueluoanping.dtpvz.event.handler;

import com.ferreusveritas.dynamictrees.api.event.TransitionSaplingToTreeEvent;
import com.ferreusveritas.dynamictrees.event.SpeciesPostGenerationEvent;
import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

import static xueluoanping.dtpvz.DTPVZ.LOGGER;
public class TreeGrowHandler {
    public static final TreeGrowHandler instance = new TreeGrowHandler();
    @SubscribeEvent
    public void onTreeGrowEvent(SpeciesPostGenerationEvent event) {
        // Random random =event.getWorld().getRandom();
        final double chance = PVZConfig.COMMON_CONFIG.BlockSettings.SaplingTurnChance.get();
        if(MathUtil.randDouble(event.getWorld().getRandom(), chance) && event.getRootPos().getY() > 2)
        {
//            LOGGER.info(""+event);
            event.getWorld().setBlock(event.getRootPos(),BlockRegister.ORIGIN_ORE.get().defaultBlockState(),0);
        }
    }

    @SubscribeEvent
    public void onTreeGrowEvent(TransitionSaplingToTreeEvent event) {
        Random random =event.getWorld().getRandom();
        // LOGGER.info(""+event+event.getPos()+event.getSpecies());
        // if (random.nextInt(16)==0)
        // {
        //     event.getWorld().setBlock(event.getPos().below(),BlockRegister.ORIGIN_ORE.get().defaultBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        // }
        if(! event.getWorld().isClientSide()) {
            final boolean isBlocked = ! event.getWorld().isEmptyBlock(event.getPos().above());
            if(PVZConfig.COMMON_CONFIG.RuleSettings.AllowNaturalTurnOrigin.get() || isBlocked) {
                final double chance = PVZConfig.COMMON_CONFIG.BlockSettings.SaplingTurnChance.get();
                if(MathUtil.randDouble(event.getWorld().getRandom(), isBlocked ? chance * 1.2 : chance) && event.getPos().getY() > 2)
                {
                    event.getWorld().setBlock(event.getPos().below(),BlockRegister.ORIGIN_ORE.get().defaultBlockState(), Constants.BlockFlags.BLOCK_UPDATE|Constants.BlockFlags.NOTIFY_NEIGHBORS);
                }
            }
        }
    }
}
