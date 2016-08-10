package au.com.auspost.postcash.service;

import au.com.auspost.postcash.domain.MoneyOrder;
import au.com.auspost.postcash.domain.MoneyOrderOrder;
import au.com.auspost.postcash.domain.MoneyOrderRequest;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.commons.codec.binary.Base64;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class MoneyOrderService {
    @Value("classpath:/barcodeConfig.xml")
    private File barcodeConfigFile;

    @Autowired
    private EmailService emailService;

    public void create(MoneyOrderRequest request) {
        for (MoneyOrderOrder order : request.getOrders()) {
            processOrder(order);
        }
    }

    private void processOrder(MoneyOrderOrder order) {
        for (MoneyOrder moneyOrder: order.getMoneyOrders()) {
            processMoneyOrder(moneyOrder);
            emailService.send(moneyOrder);
        }
    }

    private void processMoneyOrder(MoneyOrder moneyOrder) {
        moneyOrder.setCheckDigits(moneyOrder.calculateCheckDigits());
        moneyOrder.setBarcodeText(moneyOrder.calculateBarcodeText());
        buildBarcode(moneyOrder.getBarcodeText());
        moneyOrder.setBarcodeBase64(buildBarcode(moneyOrder.getBarcodeText()));
        moneyOrder.setBarcodeMimeType("image/png");
    }

    private String buildBarcode(String barcodeString) {
        DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
        try {
            Configuration cfg = builder.buildFromFile(barcodeConfigFile);
            BarcodeGenerator gen = BarcodeUtil.getInstance().createBarcodeGenerator( cfg );

            /*
            OutputStream out = new java.io.FileOutputStream(new File("output.png"));
            BitmapCanvasProvider provider = new BitmapCanvasProvider(
                    out, "image/x-png", 300, BufferedImage.TYPE_BYTE_GRAY, true, 0);
            gen.generateBarcode(provider, barcodeString);
            provider.finish();
            */

            BitmapCanvasProvider provider = new BitmapCanvasProvider(
                    300, BufferedImage.TYPE_BYTE_GRAY, true, 0);
            gen.generateBarcode(provider, barcodeString);
            provider.finish();
            BufferedImage barcodeImage = provider.getBufferedImage();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(barcodeImage, "PNG", out);
            byte[] bytes = out.toByteArray();
            return Base64.encodeBase64String(bytes);
        } catch (SAXException | IOException | ConfigurationException | BarcodeException e) {
            throw new IllegalStateException(e);
        }
    }
}
