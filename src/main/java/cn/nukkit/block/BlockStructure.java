package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityStructBlock;
import cn.nukkit.blockproperty.ArrayBlockProperty;
import cn.nukkit.blockproperty.BlockProperties;
import cn.nukkit.blockproperty.BlockProperty;
import cn.nukkit.blockproperty.value.StructureBlockType;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.NotNull;

/**
 * @author good777LUCKY
 */
@Since("1.4.0.0-PN")
@PowerNukkitOnly
public class BlockStructure extends BlockSolidMeta implements BlockEntityHolder<BlockEntityStructBlock> {
    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public static final BlockProperty<StructureBlockType> STRUCTURE_BLOCK_TYPE = new ArrayBlockProperty<>(
            "structure_block_type", true, StructureBlockType.class
    );

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public static final BlockProperties PROPERTIES = new BlockProperties(STRUCTURE_BLOCK_TYPE);

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public BlockStructure() {
        this(0);
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    public BlockStructure(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return STRUCTURE_BLOCK;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull
    @Override
    public BlockProperties getProperties() {
        return PROPERTIES;
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public StructureBlockType getStructureBlockType() {
        return getPropertyValue(STRUCTURE_BLOCK_TYPE);
    }

    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    public void setStructureBlockType(StructureBlockType type) {
        setPropertyValue(STRUCTURE_BLOCK_TYPE, type);
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(@NotNull Item item, Player player) {
        if (player != null) {
            if (player.isCreative() && player.isOp()) {
                BlockEntityStructBlock tile = this.getOrCreateBlockEntity();
                tile.spawnTo(player);
                player.addWindow(tile.getInventory());
            }
        }
        return true;
    }

    @Override
    public boolean place(@NotNull Item item, @NotNull Block block, @NotNull Block target, @NotNull BlockFace face, double fx, double fy, double fz, Player player) {
        if (player != null && (!player.isCreative() || !player.isOp())) {
            return false;
        }
        return BlockEntityHolder.setBlockAndCreateEntity(this, true, true) != null;
    }

    @Override
    public String getName() {
        return getStructureBlockType().getEnglishName();
    }

    @Override
    public double getHardness() {
        return -1;
    }

    @Override
    public double getResistance() {
        return 18000000;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public boolean isBreakable(Item item) {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    @PowerNukkitOnly
    public boolean canBePulled() {
        return false;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.LIGHT_GRAY_BLOCK_COLOR;
    }


    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull
    @Override
    public Class<? extends BlockEntityStructBlock> getBlockEntityClass() {
        return BlockEntityStructBlock.class;
    }

    @Since("1.4.0.0-PN")
    @PowerNukkitOnly
    @NotNull
    @Override
    public String getBlockEntityType() {
        return BlockEntity.STRUCTURE_BLOCK;
    }
}
