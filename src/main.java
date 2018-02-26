import java.awt.*;
import java.awt.image.*;

public class main {
    public static long rr, rg, rb;
    public static String hexr, hexg, hexb;
    public static boolean perform = true;
    public static Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());


    public static void main(String[] args) throws java.awt.AWTException, java.lang.InterruptedException{
        Robot robot = new Robot();

        do {
            BufferedImage bufferedImage = robot.createScreenCapture(screenSize); //making a screenshot
            BufferedImage smallBufferedImage = new BufferedImage((int)screenSize.getWidth()/60, (int)screenSize.getHeight()/60, BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = smallBufferedImage.createGraphics();
            g2d.drawImage(bufferedImage, 0, 0, (int)screenSize.getWidth()/60, (int)screenSize.getHeight()/60, null); //compressing the screenshot to be 60 times smaller
            g2d.dispose();

            averageColor(smallBufferedImage, 0, 0, smallBufferedImage.getWidth(), smallBufferedImage.getHeight());

            System.out.println(rr + " " + rg + " " + rb + "   #" + hexr + hexg + hexb);

            Thread.sleep(1000);
        }while(perform=true);
    }

    public static void averageColor(BufferedImage bi, int x, int y, int w, int h) {
        int xf = x + w; //full width of image
        int yf = y + h; //full height of image
        long sumr = 0, sumg = 0, sumb = 0;
        for (int i = x; i < xf; i++) {
            for (int u = y; u < yf; u++) {
                Color pixel = new Color(bi.getRGB(i, u));
                sumr += pixel.getRed();
                sumg += pixel.getGreen();
                sumb += pixel.getBlue();
            }
        }
        long num = w * h;

        rr = sumr/num; //output in rgb values
        rg = sumg/num;
        rb = sumb/num;

        hexr = Integer.toHexString((int) rr); //output in hex values
        hexg = Integer.toHexString((int) rg);
        hexb = Integer.toHexString((int) rb);
    }
}
