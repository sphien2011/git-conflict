package com.test.hien;

import android.graphics.Bitmap;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.emv.qrcode.model.mpm.PaymentSystemSpecific;
import com.emv.qrcode.model.mpm.PaymentSystemSpecificTemplate;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedTemplate;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

public class Utils {
    public static MerchantAccountInformationTemplate getMerchanAccountInformation() {

        final TagLengthString paymentNetworkSpecific = new TagLengthString();
//        paymentNetworkSpecific.setTag("05");
//        paymentNetworkSpecific.setValue("47613617");

        final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
//        merchantAccountInformationValue.setGloballyUniqueIdentifier("A0000000031010");
//        merchantAccountInformationValue.setGloballyUniqueIdentifier("123456789");
        merchantAccountInformationValue.setGloballyUniqueIdentifier("47613617");
//        merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

        final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
        merchantAccountInformation.setValue(merchantAccountInformationValue);
        merchantAccountInformation.setTag("02");

        return merchantAccountInformation;
    }

    public static MerchantAccountInformationTemplate getMerchanAccountInformation1() {
        final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
//        merchantAccountInformationValue.setGloballyUniqueIdentifier("A0000000031010");
//        merchantAccountInformationValue.setGloballyUniqueIdentifier("123456789");
        merchantAccountInformationValue.setGloballyUniqueIdentifier("D15600000000");
//        merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

        final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
        merchantAccountInformation.setValue(merchantAccountInformationValue);
        merchantAccountInformation.setTag("03");

        return merchantAccountInformation;
    }

    public static UnreservedTemplate getUnreserved() {

        final TagLengthString contextSpecificData = new TagLengthString();
        contextSpecificData.setTag("07");
        contextSpecificData.setValue("12345678");

        final Unreserved value = new Unreserved();
        value.setGloballyUniqueIdentifier("A011223344998877");
        value.addContextSpecificData(contextSpecificData);

        final UnreservedTemplate unreserved = new UnreservedTemplate();
        unreserved.setValue(value);
        unreserved.setTag("80");

        return unreserved;
    }

    public static MerchantInformationLanguageTemplate getMerchantInformationLanguage() {

        final TagLengthString rFUforEMVCo = new TagLengthString();
        rFUforEMVCo.setTag("03");
        rFUforEMVCo.setValue("abcd");

        final MerchantInformationLanguage merchantInformationLanguageValue = new MerchantInformationLanguage();
        merchantInformationLanguageValue.setLanguagePreference("ZH");
        merchantInformationLanguageValue.setMerchantName("test");
        merchantInformationLanguageValue.setMerchantCity("test123");
//        merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

        final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
        merchantInformationLanguage.setValue(merchantInformationLanguageValue);

        return merchantInformationLanguage;
    }

    public static AdditionalDataFieldTemplate getAddtionalDataField() {

//        final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
//        paymentSystemSpecific.setGloballyUniqueIdentifier("1234");
//        paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "ijkl"));
//
//        final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
//        paymentSystemSpecificTemplate.setTag("50");
//        paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);
//
//        final TagLengthString rFUforEMVCo = new TagLengthString();
//        rFUforEMVCo.setTag("10");
//        rFUforEMVCo.setValue("abcd");

        final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
//        additionalDataFieldValue.setAdditionalConsumerDataRequest("tuv");
//        additionalDataFieldValue.setMobileNumber("67890");
//        additionalDataFieldValue.setPurposeTransaction("pqres");
//        additionalDataFieldValue.setReferenceLabel("abcde");
//        additionalDataFieldValue.setStoreLabel("09876");
        additionalDataFieldValue.setTerminalLabel("0806623");
//        additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecificTemplate);
//        additionalDataFieldValue.addRFUforEMVCo(rFUforEMVCo);

        final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
        additionalDataField.setValue(additionalDataFieldValue);

        return additionalDataField;
    }

    public static Bitmap encodeAsBitmap(String str, int WIDTH, int margin, int foreground, int background) throws WriterException {
        BitMatrix result;
        try {

            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.MARGIN, margin);
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? foreground : background;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, WIDTH, 0, 0, w, h);
        return bitmap;
    }
}
