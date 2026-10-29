package featurecreep.api.bg.image;

import com.mojang.blaze3d.platform.NativeImage;

import featurecreep.api.image.FCImage;

public final class FCImageBridge {

	private FCImageBridge() {
	}

	/**
	 * The caller owns the returned image and must close it.
	 */
	public static NativeImage toNativeImage(FCImage image) {
		int width = image.width();
		int height = image.height();
		NativeImage native_image = new NativeImage(NativeImage.Format.RGBA, width, height, true);
		try {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					native_image.setPixel(x, y, image.getArgb(x, y));
				}
			}
		} catch (RuntimeException e) {
			native_image.close();
			throw e;
		}
		return native_image;
	}

	public static FCImage fromNativeImage(NativeImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[y * width + x] = image.getPixel(x, y);
			}
		}
		return FCImage.of(width, height, pixels);
	}
}
