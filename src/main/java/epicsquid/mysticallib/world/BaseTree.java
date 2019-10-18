package epicsquid.mysticallib.world;

import net.minecraft.block.Block;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class BaseTree extends Tree {

  private final int size;
  private final Supplier<Block> log;
  private final Supplier<Block> leaves;

  public BaseTree(int size, Supplier<Block> log, Supplier<Block> leaves) {
    this.size = size;
    this.log = log;
    this.leaves = leaves;
  }

  public AbstractTreeFeature<NoFeatureConfig> getTreeFeaturePublic(Random rand) {
    return getTreeFeature(rand);
  }

  @Nullable
  @Override
  protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
    return new TreeFeature(NoFeatureConfig::deserialize, true, size, log.get().getDefaultState(), leaves.get().getDefaultState(), false);
  }
}
