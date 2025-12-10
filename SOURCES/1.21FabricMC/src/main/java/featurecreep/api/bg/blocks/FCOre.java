package featurecreep.api.bg.blocks;

import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import io.smallrye.common.constraint.Nullable;

public class FCOre extends DropExperienceBlock implements FCBlockAPI<FCOre> {

	public BlockFieldHolder holder = new BlockFieldHolder();

	@Override
	public BlockFieldHolder holder() {
		return holder;
	}

	public Object resource;

	public FCOre(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material,
			int strength, BlockDropArrayObject[] drops, Object ore_material) {
		super(ConstantInt.of(0), Properties.of().destroyTime(strength / 10f));// Need to add material again
																							// soon
		initialise(id, modid, name, group, material, strength, drops);
		resource = ore_material;
	}

	@Override
	public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity,
			ItemStack stack) {
		player.awardStat(Stats.BLOCK_MINED.get(this));
		player.causeFoodExhaustion(0.005f);

		for (int i = 0; i < getDropArrayObjects().length; i++) {
			if (getDropArrayObjects()[i].getTool.equals(ToolTypes.BLANK)) {

				System.out.println("Right tool used Dropping items");

				getDrops(world, pos, getDropArrayObjects()[i]);

			} else {
				System.out.println(stack.getItem().toString()); // Took so damn long to realise i needed stack and not
																// player.ActiveItem

				// System.out.println(stack.getItem().getClass().isAssignableFrom(getDropArrayObjects()[i].getTool.get));
				System.out.println(getDropArrayObjects()[i].getTool.get.isAssignableFrom(stack.getItem().getClass()));

				if (getDropArrayObjects()[i].getTool.get.isAssignableFrom(stack.getItem().getClass())) {
					System.out.println("You used the right tool");
					getDrops(world, pos, getDropArrayObjects()[i]);
				} else {
					System.out.print("Wrong Tool Used For This Array, you need an instance of"
							+ getDropArrayObjects()[i].getTool.get.getCanonicalName() + "But instead got"
							+ player.getUseItem().getClass().getName());
				}

			}

		}

	}

	private void getDrops(Level world, BlockPos pos, BlockDropArrayObject loot) {

		if (loot instanceof featurecreep.api.bg.blocks.drop.SelfBlockDropArrayObject) {
			System.out.println("Dropping Self");
			Block.popResource(world, pos, new ItemStack(this));
		} else {
			for (int t = 0; t < loot.drop.size(); t++) {
				System.out.println("Right tool used");
				if (loot.drop.get(t) instanceof Block) {
					Block.popResource(world, pos, new ItemStack((Block) loot.drop.get(t)));
					// Block.dropStacks(state, world, pos, blockEntity, player, new
					// ItemStack((Block) getDropArrayObjects()[i].drop.get(t)));
				} else {
					Block.popResource(world, pos, new ItemStack((Item) loot.drop.get(t)));
					// Block.dropStacks(state, world, pos, blockEntity, player, new ItemStack((Item)
					// getDropArrayObjects()[i].drop.get(t)));
				} // Gotta do entites soon

			}
		}

	}

	@Override
	public FCOre isSingleSided(boolean answer) {
		holder().single_sided = answer;
		return this;
	}

}