package swing.shadow;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ShadowRenderer {

    private int size = 5;
    private float opacity = 0.5f;
    private Color color = Color.BLACK;

    public ShadowRenderer() {
        this(5, 0.5f, Color.BLACK);
    }

    public ShadowRenderer(final int size, final float opacity, final Color color) {
        this.size = size;
        this.opacity = opacity;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public float getOpacity() {
        return opacity;
    }

    public int getSize() {
        return size;
    }

    public BufferedImage createShadow(final BufferedImage image) {
        int shadowSize = size * 2;
        int srcWidth = image.getWidth();
        int srcHeight = image.getHeight();
        int dstWidth = srcWidth + shadowSize;
        int dstHeight = srcHeight + shadowSize;
        int left = size;
        int right = shadowSize - left;
        int yStop = dstHeight - right;
        int shadowRgb = color.getRGB() & 0x00FFFFFF;
        int[] aHistory = new int[shadowSize];
        int aSum;
        BufferedImage dst = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_ARGB);
        int[] dstBuffer = new int[dstWidth * dstHeight];
        int[] srcBuffer = new int[srcWidth * srcHeight];
        GraphicsUtilities.getPixels(image, 0, 0, srcWidth, srcHeight, srcBuffer);
        float hSumDivider = 1.0f / shadowSize;
        float vSumDivider = opacity / shadowSize;
        int[] hSumLookup = new int[256 * shadowSize];
        int[] vSumLookup = new int[256 * shadowSize];

        // Precompute the lookup tables
        for (int i = 0; i < hSumLookup.length; i++) {
            hSumLookup[i] = (int) (i * hSumDivider);
        }
        for (int i = 0; i < vSumLookup.length; i++) {
            vSumLookup[i] = (int) (i * vSumDivider);
        }

        // Horizontal pass
        for (int srcY = 0; srcY < srcHeight; srcY++) {
            aSum = 0;
            int historyIdx = 0;
            int dstOffset = left * dstWidth + srcY * dstWidth; // Calculate dstOffset for the current row

            // Initialize history
            for (int i = 0; i < shadowSize; i++) {
                aHistory[i] = 0;
            }

            for (int srcX = 0; srcX < srcWidth; srcX++) {
                int a = srcBuffer[srcY * srcWidth + srcX] >>> 24;
                aSum -= aHistory[historyIdx];
                aHistory[historyIdx] = a;
                aSum += a;

                // Ensure aSum is within bounds
                aSum = Math.max(0, Math.min(aSum, hSumLookup.length - 1));

                // Calculate the destination index
                int dstIndex = dstOffset + srcX; // Calculate the index based on srcX
                dstBuffer[dstIndex] = hSumLookup[aSum] << 24; // Assign the value

                // Update history index
                historyIdx = (historyIdx + 1) % shadowSize;
            }

            // Fill remaining shadow pixels
            for (int i = 0; i < shadowSize; i++) {
                aSum -= aHistory[historyIdx];

                // Ensure aSum is within bounds
                aSum = Math.max(0, Math.min(aSum, hSumLookup.length - 1));

                // Calculate the destination index
                int dstIndex = dstOffset + srcWidth + i; // Calculate the index for remaining pixels
                dstBuffer[dstIndex] = hSumLookup[aSum] << 24; // Assign the value

                // Update history index
                historyIdx = (historyIdx + 1) % shadowSize;
            }
        }

        // Vertical pass
        for (int x = 0; x < dstWidth; x++) {
            aSum = 0;
            int historyIdx = 0;

            // Initialize history
            for (int i = 0; i < left; i++) {
                aHistory[i] = 0;
            }

            for (int y = 0; y < right; y++) {
                int a = dstBuffer[y * dstWidth + x] >>> 24;
                aHistory[historyIdx++] = a;
                aSum += a;
            }

            historyIdx = 0;
            for (int y = 0; y < yStop; y++) {
                // Ensure aSum is within bounds
                aSum = Math.max(0, Math.min(aSum, vSumLookup.length - 1));

                // Calculate the destination index
                int dstIndex = y * dstWidth + x; // Calculate the index for the current pixel
                dstBuffer[dstIndex] = (vSumLookup[aSum] << 24) | shadowRgb; // Assign the value
                aSum -= aHistory[historyIdx];
                int a = dstBuffer[(y + right) * dstWidth + x] >>> 24;
                aHistory[historyIdx] = a;
                aSum += a;

                // Update history index
                historyIdx = (historyIdx + 1) % shadowSize;
            }

            for (int y = yStop; y < dstHeight; y++) {
                // Ensure aSum is within bounds
                aSum = Math.max(0, Math.min(aSum, vSumLookup.length - 1));

                // Calculate the destination index
                int dstIndex = y * dstWidth + x; // Calculate the index for the current pixel
                dstBuffer[dstIndex] = (vSumLookup[aSum] << 24) | shadowRgb; // Assign the value
                aSum -= aHistory[historyIdx];

                // Update history index
                historyIdx = (historyIdx + 1) % shadowSize;
            }
        }

        GraphicsUtilities.setPixels(dst, 0, 0, dstWidth, dstHeight, dstBuffer);
        return dst;
    }
}
