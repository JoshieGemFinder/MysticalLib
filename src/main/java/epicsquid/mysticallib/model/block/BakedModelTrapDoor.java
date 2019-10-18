package epicsquid.mysticallib.model.block;

import epicsquid.mysticallib.model.CustomModelBase;
import epicsquid.mysticallib.model.ModelUtil;
import epicsquid.mysticallib.model.parts.Cube;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.state.properties.Half;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class BakedModelTrapDoor extends BakedModelBlock {

  private Cube cube_down, cube_up, cube_east, cube_west, cube_south, cube_north;

  public BakedModelTrapDoor(@Nonnull IModelState state, @Nonnull VertexFormat format,
                            @Nonnull Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, @Nonnull CustomModelBase model) {
    super(format, bakedTextureGetter, model);
    TextureAtlasSprite[] texes = new TextureAtlasSprite[]{texwest, texeast, texdown, texup, texnorth, texsouth};
    cube_down = ModelUtil.makeCube(format, 0, 0, 0, 1, 0.1875, 1, null, texes, 0).setNoCull(Direction.DOWN);
    cube_up = ModelUtil.makeCube(format, 0, 0.8125, 0, 1, 0.1875, 1, null, texes, 0).setNoCull(Direction.UP);
    cube_west = ModelUtil.makeCube(format, 0.8125, 0, 0, 0.1875, 1, 1, null, texes, 0).setNoCull(Direction.WEST);
    cube_east = ModelUtil.makeCube(format, 0, 0, 0, 0.1875, 1, 1, null, texes, 0).setNoCull(Direction.EAST);
    cube_north = ModelUtil.makeCube(format, 0, 0, 0.8125, 1, 1, 0.1875, null, texes, 0).setNoCull(Direction.NORTH);
    cube_south = ModelUtil.makeCube(format, 0, 0, 0, 1, 1, 0.1875, null, texes, 0).setNoCull(Direction.SOUTH);
  }

  @Override
  @Nonnull
  public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
    List<BakedQuad> quads = new ArrayList<>();
    getFaceQuads(quads, side, state);
    return quads;
  }

  private void getFaceQuads(@Nonnull List<BakedQuad> quads, @Nullable Direction side, @Nullable BlockState state) {
    if (state == null) {
      cube_down.addToList(quads, side);
    } else {
      Half half = state.get(TrapDoorBlock.HALF);
      boolean open = state.get(TrapDoorBlock.OPEN);
      if (!open) {
        if (half == Half.BOTTOM) {
          cube_down.addToList(quads, side);
        } else {
          cube_up.addToList(quads, side);
        }
      } else {
        switch (state.get(TrapDoorBlock.HORIZONTAL_FACING)) {
          case EAST:
            cube_east.addToList(quads, side);
            break;
          case SOUTH:
            cube_south.addToList(quads, side);
            break;
          case WEST:
            cube_west.addToList(quads, side);
            break;
          case NORTH:
            cube_north.addToList(quads, side);
            break;
        }
      }
    }
  }
}
