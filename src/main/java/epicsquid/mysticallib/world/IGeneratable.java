package epicsquid.mysticallib.world;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public interface IGeneratable {

  void generateIn(@Nonnull World world, int x, int y, int z, @Nonnull Rotation rotation, @Nonnull Mirror doMirror, boolean replaceWithAir, boolean force);

  void calcDimensions();

  int getWidth();

  int getLength();
}
