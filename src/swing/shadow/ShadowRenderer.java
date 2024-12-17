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

        int[][] lookupTables = precomputeLookupTables(shadowSize, opacity);
        int[] hSumLookup = lookupTables[0];
        int[] vSumLookup = lookupTables[1];

        applyHorizontalPass(srcBuffer, dstBuffer, srcWidth, srcHeight, dstWidth, left, shadowSize, hSumLookup);
        applyVerticalPass(dstBuffer, dstWidth, dstHeight, shadowRgb, left, right, yStop, shadowSize, vSumLookup);

        GraphicsUtilities.setPixels(dst, 0, 0, dstWidth, dstHeight, dstBuffer);
        return dst;
    }

    private int[][] precomputeLookupTables(int shadowSize, float opacity) {
        float hSumDivider = 1.0f / shadowSize;
        float vSumDivider = opacity / shadowSize;

        int[] hSumLookup = new int[256 * shadowSize];
        int[] vSumLookup = new int[256 * shadowSize];

        for (int i = 0; i < hSumLookup.length; i++) {
            hSumLookup[i] = (int) (i * hSumDivider);
        }
        for (int i = 0; i < vSumLookup.length; i++) {
            vSumLookup[i] = (int) (i * vSumDivider);
        }

        return new int[][]{hSumLookup, vSumLookup};
    }

    private void applyHorizontalPass(int[] srcBuffer, int[] dstBuffer, int srcWidth, int srcHeight, int dstWidth, int left, int shadowSize, int[] hSumLookup) {
        int[] aHistory = new int[shadowSize];
        int aSum;

        for (int srcY = 0; srcY < srcHeight; srcY++) {
            aSum = 0;
            int historyIdx = 0;
            int dstOffset = left * dstWidth + srcY * dstWidth;

            resetHistory(aHistory);

            for (int srcX = 0; srcX < srcWidth; srcX++) {
                aSum = updateASum(aHistory, historyIdx, aSum, srcBuffer[srcY * srcWidth + srcX] >>> 24, shadowSize, hSumLookup);
                int dstIndex = dstOffset + srcX;
                dstBuffer[dstIndex] = hSumLookup[aSum] << 24;
                historyIdx = (historyIdx + 1) % shadowSize;
            }

            fillRemainingShadowPixels(dstBuffer, aHistory, dstOffset + srcWidth, shadowSize, hSumLookup, historyIdx, aSum);
        }
    }

    private void applyVerticalPass(int[] dstBuffer, int dstWidth, int dstHeight, int shadowRgb, int left, int right, int yStop, int shadowSize, int[] vSumLookup) {
        int[] aHistory = new int[shadowSize];
        int aSum;

        for (int x = 0; x < dstWidth; x++) {
            aSum = 0;
            int historyIdx = 0;

            resetHistory(aHistory);

            for (int y = 0; y < right; y++) {
                aHistory[historyIdx++] = dstBuffer[y * dstWidth + x] >>> 24;
                aSum += aHistory[historyIdx];
            }

            historyIdx = 0;
            for (int y = 0; y < yStop; y++) {
                aSum = updateASum(aHistory, historyIdx, aSum, dstBuffer[(y + right) * dstWidth + x] >>> 24, shadowSize, vSumLookup);
                dstBuffer[y * dstWidth + x] = (vSumLookup[aSum] << 24) | shadowRgb;
                historyIdx = (historyIdx + 1) % shadowSize;
            }

            fillRemainingShadowPixels(dstBuffer, aHistory, x + yStop * dstWidth, dstHeight - yStop, vSumLookup, historyIdx, aSum);
        }
    }

    private void resetHistory(int[] aHistory) {
        for (int i = 0; i < aHistory.length; i++) {
            aHistory[i] = 0;
        }
    }

    private int updateASum(int[] aHistory, int historyIdx, int aSum, int alpha, int shadowSize, int[] sumLookup) {
        aSum -= aHistory[historyIdx];
        aHistory[historyIdx] = alpha;
        aSum += alpha;
        return Math.max(0, Math.min(aSum, sumLookup.length - 1));
    }

    private void fillRemainingShadowPixels(int[] dstBuffer, int[] aHistory, int dstOffset, int shadowSize, int[] sumLookup, int historyIdx, int aSum) {
        for (int i = 0; i < shadowSize; i++) {
            aSum -= aHistory[historyIdx];
            aSum = Math.max(0, Math.min(aSum, sumLookup.length - 1));
            dstBuffer[dstOffset + i] = sumLookup[aSum] << 24;
            historyIdx = (historyIdx + 1) % shadowSize;
        }
    }
}
