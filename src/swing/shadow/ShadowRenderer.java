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

        BufferedImage dst = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_ARGB);
        int[] dstBuffer = new int[dstWidth * dstHeight];
        int[] srcBuffer = new int[srcWidth * srcHeight];
        GraphicsUtilities.getPixels(image, 0, 0, srcWidth, srcHeight, srcBuffer);

        int[] hSumLookup = createSumLookup(shadowSize, 1.0f / shadowSize);
        int[] vSumLookup = createSumLookup(shadowSize, opacity / shadowSize);
        
        createHorizontalShadow(srcBuffer, dstBuffer, srcWidth, srcHeight, left, right, hSumLookup);
        createVerticalShadow(dstBuffer, dstWidth, dstHeight, left, right, yStop, shadowRgb, vSumLookup);

        GraphicsUtilities.setPixels(dst, 0, 0, dstWidth, dstHeight, dstBuffer);
        return dst;
    }

    private int[] createSumLookup(int size, float divider) {
        int[] lookup = new int[256 * size];
        for (int i = 0; i < lookup.length; i++) {
            lookup[i] = (int) (i * divider);
        }
        return lookup;
    }

    private void resetHistory(int[] history) {
        for (int i = 0; i < history.length; i++) {
            history[i] = 0;
        }
    }

    private void createHorizontalShadow(int[] srcBuffer, int[] dstBuffer, int srcWidth, int srcHeight, int left, int right, int[] hSumLookup) {
        int[] aHistory = new int[left + right];
        int historyIdx;
        int aSum;

        for (int srcY = 0, dstOffset = left * (srcWidth + right); srcY < srcHeight; srcY++) {
            resetHistory(aHistory);
            aSum = 0;
            historyIdx = 0;
            int srcOffset = srcY * srcWidth;

            for (int srcX = 0; srcX < srcWidth; srcX++) {
                int a = hSumLookup[aSum];
                dstBuffer[dstOffset++] = a << 24;
                aSum -= aHistory[historyIdx];
                a = srcBuffer[srcOffset + srcX] >>> 24;
                aHistory[historyIdx] = a;
                aSum += a;

                if (++historyIdx >= left + right) {
                    historyIdx -= left + right;
                }
            }

            fillRemainingHorizontalShadow(dstBuffer, dstOffset, hSumLookup, aSum, historyIdx, right);
        }
    }

    private void fillRemainingHorizontalShadow(int[] dstBuffer, int dstOffset, int[] hSumLookup, int aSum, int historyIdx, int right) {
        for (int i = 0; i < right; i++) {
            int a = hSumLookup[aSum];
            dstBuffer[dstOffset++] = a << 24;
            aSum -= 0; // Placeholder for history management
        }
    }

    private void createVerticalShadow(int[] dstBuffer, int dstWidth, int dstHeight, int left, int right, int yStop, int shadowRgb, int[] vSumLookup) {
        int[] aHistory = new int[left + right];
        int aSum;
        int lastPixelOffset = right * dstWidth;

        for (int x = 0; x < dstWidth; x++) {
            resetHistory(aHistory);
            aSum = 0;

            // Accumulate initial vertical shadow values
            for (int y = 0; y < right; y++) {
                int bufferOffset = x + y * dstWidth;
                int a = dstBuffer[bufferOffset] >>> 24;
                aHistory[y] = a;
                aSum += a;
            }

            // Process main vertical shadow area
            for (int y = 0; y < yStop; y++) {
                int bufferOffset = x + y * dstWidth;
                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;
                
                aSum -= aHistory[y % (left + right)];
                
                int nextA = dstBuffer[bufferOffset + lastPixelOffset] >>> 24;
                aHistory[y % (left + right)] = nextA;
                
                aSum += nextA;
            }

            // Process remaining bottom area
            for (int y = yStop; y < dstHeight; y++) {
                int bufferOffset = x + y * dstWidth;
                int a = vSumLookup[aSum];
                dstBuffer[bufferOffset] = a << 24 | shadowRgb;
                
                aSum -= aHistory[y % (left + right)];
            }
        }
    }
}