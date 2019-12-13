package model.process;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import image.PixelGenerator;
import model.AbstractImageProcessor;

/**
 * This class supports function that transfer one image to mosaic style using single
 * iteration cluster, the cluster number should be given.
 */
public class MosaicImage extends AbstractImageProcessor {

  /**
   * The number of seeds, which is the total number of clusters in the algorithm.
   */
  private int seedNum;

  /**
   * Constructor taking seed number and an image waiting for processing.
   *
   * @param seedNum number of cluster - mosaic chunks
   * @param image   preprocessing image
   */
  public MosaicImage(int seedNum, BufferedImage image) {
    super(image);
    if (seedNum <= 0) {
      throw new IllegalArgumentException("Seed number cannot be zero or negative.");
    }
    if (seedNum > image.getHeight() * image.getWidth()) {
      throw new IllegalArgumentException("Seed number cannot be more than the image pixel number.");
    }
    this.seedNum = seedNum;
  }

  /**
   * Processing image to mosaic style. Find cluster centers randomly, cluster other pixels
   * according to distance, get average rgb color of each cluster then paint it.
   *
   * @return mosaic style image
   */
  @Override
  public BufferedImage process() {
    Map<PixelPos, List<PixelPos>> clusters = getEmptyClusters();
    fulfillClusters(clusters);
    Map<PixelPos, RGBColor> targetColor = new HashMap<>();
    for (Map.Entry<PixelPos, List<PixelPos>> entry : clusters.entrySet()) {
      RGBColor averageColor = getAverageColor(entry.getValue());
      targetColor.put(entry.getKey(), averageColor);
    }
    return mosaic(clusters, targetColor);
  }

  /**
   * Get cluster centers randomly. The number is the seed number.
   *
   * @return a map whose keys are cluster centers and values are empty list
   */
  private Map<PixelPos, List<PixelPos>> getEmptyClusters() {
    Map<PixelPos, List<PixelPos>> clusters = new HashMap<>();
    Random random = new Random();
    int pickedSeedNum = 0;
    int maxLength = imageHeight * imageWidth;
    boolean[] clusterCenters = new boolean[maxLength];
    while (pickedSeedNum < seedNum) {
      int seedIdx = random.nextInt(maxLength);
      if (!clusterCenters[seedIdx]) {
        clusterCenters[seedIdx] = true;
        pickedSeedNum++;
        int row = seedIdx / imageWidth;
        int col = seedIdx % imageWidth;
        clusters.put(new PixelPos(row, col), new ArrayList<>());
      }
    }
    return clusters;
  }

  /**
   * Cluster each pixel to cluster centers using distance.
   *
   * @param clusters a map whose keys are cluster centers and values are empty lists
   */
  private void fulfillClusters(Map<PixelPos, List<PixelPos>> clusters) {
    Set<Map.Entry<PixelPos, List<PixelPos>>> entries = clusters.entrySet();
    for (int h = 0; h < imageHeight; h++) {
      for (int w = 0; w < imageWidth; w++) {
        Map.Entry<PixelPos, List<PixelPos>> closestCluster = null;
        int closestDistance = Integer.MAX_VALUE;
        PixelPos cur = new PixelPos(h, w);
        for (Map.Entry<PixelPos, List<PixelPos>> entry : entries) {
          PixelPos center = entry.getKey();
          int distance = getDistance(center, cur);
          if (distance < closestDistance) {
            closestDistance = distance;
            closestCluster = entry;
          }
        }
        closestCluster.getValue().add(cur);
      }
    }
  }

  /**
   * Get the distance between two pixels according to Euclide distance but without sqrt.
   *
   * @param a pixel a
   * @param b pixel b
   * @return distance without sqrt
   */
  private int getDistance(PixelPos a, PixelPos b) {
    int rowDiff = a.row - b.row;
    int colDiff = a.col - b.col;
    return rowDiff * rowDiff + colDiff * colDiff;
  }

  /**
   * Get the average rgb color of given cluster.
   *
   * @param cluster given cluster
   * @return average color of rgb
   */
  private RGBColor getAverageColor(List<PixelPos> cluster) {
    RGBColor color = new RGBColor(0, 0, 0);
    for (PixelPos pixel : cluster) {
      int rGB = image.getRGB(pixel.col, pixel.row);
      int red = (rGB >> 16) & 0xff;
      int green = (rGB >> 8) & 0xff;
      int blue = (rGB) & 0xff;
      color.r += red;
      color.g += green;
      color.b += blue;
    }
    int number = cluster.size();
    color.r /= number;
    color.g /= number;
    color.b /= number;
    return color;
  }

  /**
   * Get mosaic style of image from given image and clusters also their average color.
   *
   * @param clusters    clusters
   * @param targetColor average color of each cluster
   * @return new mosaic image
   */
  private BufferedImage mosaic(Map<PixelPos, List<PixelPos>> clusters, Map<PixelPos,
          RGBColor> targetColor) {
    BufferedImage newImage = new BufferedImage(imageWidth, imageHeight,
            BufferedImage.TYPE_INT_RGB);
    for (Map.Entry<PixelPos, List<PixelPos>> cluster : clusters.entrySet()) {
      RGBColor color = targetColor.get(cluster.getKey());
      for (PixelPos pixel : cluster.getValue()) {
        int rgb = PixelGenerator.aRGBGenerator(255, color.r, color.g, color.b);
        newImage.setRGB(pixel.col, pixel.row, rgb);
      }
    }
    return newImage;
  }

  /**
   * The rGB color class for rGB.
   */
  private static class RGBColor {
    /**
     * Color red.
     */
    int r;

    /**
     * Color green.
     */
    int g;

    /**
     * Color blue.
     */
    int b;

    /**
     * Initialize rgb colors.
     *
     * @param r red
     * @param g green
     * @param b blue
     */
    public RGBColor(int r, int g, int b) {
      this.r = r;
      this.g = g;
      this.b = b;
    }
  }

  /**
   * Class for pixel positions.
   */
  private static class PixelPos {
    /**
     * Row of pixel.
     */
    int row;

    /**
     * Column of pixel.
     */
    int col;

    /**
     * Initialize the position of pixel.
     *
     * @param row row
     * @param col column
     */
    public PixelPos(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
}
