package au.com.auspost.postcash.service;

import au.com.auspost.postcash.domain.MoneyOrder;
import au.com.auspost.postcash.domain.MoneyOrderOrder;
import au.com.auspost.postcash.domain.MoneyOrderRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
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
import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
        try {
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map hintMap = new HashMap();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            BitMatrix matrix = null;
                matrix = new MultiFormatWriter().encode(
                        new String(barcodeString.getBytes(charset), charset),
                        BarcodeFormat.QR_CODE, 200, 200, hintMap);

            BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(matrix);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(barcodeImage, "PNG", out);
            byte[] bytes = out.toByteArray();
            return Base64.encodeBase64String(bytes);
        } catch (WriterException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
