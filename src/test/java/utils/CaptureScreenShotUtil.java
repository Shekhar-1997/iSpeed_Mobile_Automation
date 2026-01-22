package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class CaptureScreenShotUtil extends BaseTest {

    public static byte[] captureAndResizeScreenshot(int targetWidth, int targetHeight, float compressionQuality) {
        byte[] compressedScreenshotBytes = new byte[0];
        try {
            // Capture the screenshot as bytes
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Convert the byte array into a BufferedImage
            ByteArrayInputStream inputStream = new ByteArrayInputStream(screenshotBytes);
            BufferedImage originalImage = ImageIO.read(inputStream);

            // Create a new image with the target dimensions and apply high-quality scaling
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();

            // Apply rendering hints for high-quality scaling
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the original image to the resized image
            g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            g.dispose();

            // Compress the image
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Get the JPEG writer and set up compression parameters
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
            if (!writers.hasNext()) throw new IllegalStateException("No writers found");
            ImageWriter writer = writers.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(outputStream);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(compressionQuality); // Set the compression quality (0.0 - 1.0)

            // Write the compressed image to the output stream
            writer.write(null, new javax.imageio.IIOImage(resizedImage, null, null), param);
            ios.close();
            writer.dispose();

            compressedScreenshotBytes = outputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressedScreenshotBytes;
    }

}
